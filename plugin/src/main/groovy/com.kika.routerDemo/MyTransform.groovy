package com.kika.routerDemo

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

class MyTransform extends Transform {

    Operator mOperator=new Operator()
    @Override
    String getName() {
        return "MyTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        println '=============== Mytransform begins ==============='
        transformInvocation.getInputs().each { TransformInput input ->
            input.directoryInputs.each { DirectoryInput directoryInput ->
                handleDirectory(directoryInput, transformInvocation.getOutputProvider())
            }
            input.jarInputs.each { JarInput jarInput ->
                handleJar(jarInput, transformInvocation.getOutputProvider(), transformInvocation.incremental)
            }
        }
        println '=============== Mytransform ends ==============='

    }

    void handleDirectory(DirectoryInput directoryInput, TransformOutputProvider transformOutputProvider) {
        System.out.println("============= handle directory " + directoryInput.file.getAbsolutePath() + " ==============")
        if (directoryInput.file.isDirectory()) {
            directoryInput.file.eachFileRecurse {
                File file ->
                    def name = file.name
                    if (name.endsWith(".class") && !name.startsWith("R\$") &&
                            !"R.class".equals(name) && !"BuildConfig.class".equals(name)) {
                        ClassReader classReader = new ClassReader(file.bytes)
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor classVisitor = new DirectoryOperator(classWriter)
                        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                        byte[] code = classWriter.toByteArray()
                        FileOutputStream fileOutputStream = new FileOutputStream(file.parentFile.absolutePath + File.separator + name)
                        System.out.println(file.parentFile.absolutePath + File.separator + name)
                        fileOutputStream.write(code)
                        System.out.println("===========$name has changed============")
                        fileOutputStream.close()
                    }
            }
        }
        def dest = transformOutputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
        FileUtils.copyDirectory(directoryInput.file,dest)
    }

    void handleJar(JarInput jarInput, TransformOutputProvider outputProvider, boolean incremental) {
        def path = jarInput.getFile().getAbsolutePath()
        println "==================== handle jar $path ========================="
        def jarName = jarInput.name
        def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
        if (jarName.endsWith(".jar")) {
            jarName = jarName.substring(0, jarName.length() - 4)
        }

        File tempFile = null
        if (jarInput.file.getAbsolutePath().endsWith(".jar")) {
            System.out.println("~~~~~~~~~~~~~~Enters jar $jarName ~~~~~~~~~~~~")
            JarFile jarFile = new JarFile(jarInput.file)
            Enumeration enumeration = jarFile.entries()
            tempFile=new File(jarInput.file.getParent()+File.separator+"classes_RouterDemo.jar")
            if (tempFile.exists())
            {
                tempFile.delete()
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tempFile))
            mOperator.appendClassPath(path)
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                def entryName = jarEntry.getName()
                ZipEntry zipEntry = new ZipEntry(entryName)
                def jarClassName = entryName.split("\\.class")[0].replaceAll("/", ".")
                byte[] b = mOperator.handleJar(jarClassName, entryName)

                if (b != null) {
                    jarOutputStream.putNextEntry(zipEntry)
                    jarOutputStream.write(b)
                }
                jarOutputStream.closeEntry()
            }
            jarOutputStream.close()
        }
        def dest = outputProvider.getContentLocation(jarName + md5Name,
                jarInput.contentTypes, jarInput.scopes, Format.JAR)
        System.out.println(dest.toString())
        if(tempFile == null)
        {
            FileUtils.copyFile(jarInput.file,dest)
        }
        else{
            FileUtils.copyFile(tempFile,dest)
            tempFile.delete()
        }
    }
}