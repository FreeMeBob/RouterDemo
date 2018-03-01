package com.kika.routerDemo

import javassist.ClassPool
import javassist.CtClass
import javassist.CtConstructor
import javassist.CtMethod
import javassist.NotFoundException
import javassist.bytecode.Descriptor

class Operator {
    ClassPool mPool = ClassPool.getDefault();
    Configuration config
    Operator(Configuration config, String androidJar){
        this.config=config
        mPool.insertClassPath(androidJar)
    }
    def appendClassPath(String classPath) {
        mPool.appendClassPath(classPath)
    }

    def handleClass(String rootDir, String className) {
        println("============== handle className " + className + " ===================")

        CtClass cc = mPool.get(className);

        if (cc.hasAnnotation("com.example.my_annotation.Module")) {
            CtMethod[] methods = cc.getDeclaredMethods();
            println "********************** handle  module *************************"
            for (int i = 0; i < methods.length; i++) {
                CtMethod method = methods[i]
                String methodName = method.getName()
                if (method.hasAnnotation("com.example.my_annotation.AutoWire")) {
                    System.out.println("============ $methodName ============")
                    String str = """System.out.println("This is a test")"""
                    method.insertBefore(str)
                    CtConstructor[] constructor = cc.getConstructor()
                    constructor[0].setBody("this.num=6")

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
                    CtMethod[] methods = cc.getDeclaredMethods();

                    println "********************** handle  module *************************"
                    for (int i = 0; i < methods.length; i++) {
                        CtMethod method = methods[i]
                        String methodName = method.getName()
                        if (method.hasAnnotation("com.example.my_annotation.AutoWire")) {
                            System.out.println("============ $methodName ============")
                            String str = """System.out.println("This is a test");"""
                            method.insertBefore(str)
                            CtConstructor[] constructors= cc.getConstructors()
                            for(CtConstructor t:constructors){
                                System.out.println(t)
                            }
                            //String despriptor = Descriptor.ofConstructor(new CtClass[0])
                            //CtConstructor constructor = cc.getConstructor(despriptor)
                            String classname=config.getClassName()
                            String methodname=config.getMethod()
                            String params=config.getParams()
                            System.out.println("11111111")
                           String mInsert = """try {
            Class utilClass = Class.forName("com.example.xinmei.routerdemo.Util");
            java.lang.reflect.Method utilMethod = utilClass.getMethod("getCalculator", new Class[0]);
            this.object = utilMethod.invoke(null, null);
            this.context=context;
            java.lang.reflect.Method utilMethod2 = utilClass.getMethod("getContextTest", new Class[]{android.content.Context.class});
            System.out.println("context in module b is :"+context);
            this.object2=utilMethod2.invoke(null,new Object[]{context});
        } catch (Exception e) {
            e.printStackTrace();
        }"""
                            constructors[1].setBody(mInsert)
                            System.out.println("22222222")
                        }
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
