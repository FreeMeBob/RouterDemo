package com.kika.routerDemo

import com.android.build.gradle.AppExtension
import org.gradle.api.Project
import org.gradle.api.Plugin

class PluginEntry implements Plugin<Project> {

    private Configuration mConfig= new Configuration()
    void apply(Project project) {
        System.out.println("===========plugin init===========")
        def android=project.extensions.getByType(AppExtension)
        def objectList=project.extensions.create('objectList',ObjectList)
        System.out.println(objectList)
        List<File> list = android.getBootClasspath()
        String androidJar = list[0]
        android.registerTransform(new MyTransform(mConfig,androidJar))
        project.afterEvaluate {
            mConfig.start(objectList)
        }
    }
}