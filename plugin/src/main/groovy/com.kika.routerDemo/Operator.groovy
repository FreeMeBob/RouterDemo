package com.kika.routerDemo

import javassist.ClassPool
import javassist.CtClass
import javassist.CtConstructor
import javassist.CtMethod
import javassist.NotFoundException
import javassist.*
import javassist.bytecode.Descriptor

class Operator {
    ClassPool mPool = ClassPool.getDefault();


    def appendClassPath(String classPath) {
        mPool.appendClassPath(classPath)
    }

    def handleClass(String rootDir, String className) {
        println("============== handle className " + className + " ===================")

        CtClass cc = mPool.get(className);

        if (cc.hasAnnotation("com.kikatech.lego.annotation.Module") && !mConf.isBlockEnable(className)) {
            CtMethod[] methods = cc.getDeclaredMethods();
            println "********************** handle  module *************************"
            for (int i = 0; i < methods.length; i++) {
                CtMethod method = methods[i]
                String methodName = method.getName()
                if (method.hasAnnotation("com.kikatech.lego.annotation.Export")) {
                    println "********************* export method " + methodName + " **********************"
                    StringBuilder sb = new StringBuilder(100)
                    CtClass returnType = method.getReturnType()
                    sb.append("return " + BlockUtils.getZeroValue(returnType.getName()) + ";")
                    println("-------------------------------- method body -------------------- \n" + sb.toString())
                    method.setBody(sb.toString())
                } else {
                    cc.removeMethod(method)
                }
            }
            cc.writeFile(rootDir)
            cc.detach()
        }
    }

    def handleJar(String className, entryName) {

        try {
            if (entryName.endsWith(".class")) {
                println("============== handle className " + className + " ===================")
                CtClass cc = mPool.get(className)
                if (cc.hasAnnotation("com.example.my_annotation.Module")) {
                    println "********************** handle  module *************************"
                    if(cc.hasAnnotation("com.example.my_annotation.AutoWireTo")){

                    }
                }
                byte[] code = cc.toBytecode()
                cc.detach()
                return code
            }
        }
        catch (NotFoundException e) {
            e.printStackTrace()
        }
        return null
    }

}
