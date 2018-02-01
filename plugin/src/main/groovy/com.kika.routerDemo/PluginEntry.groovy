package com.kika.routerDemo

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project;
import org.gradle.api.Plugin

class PluginEntry implements Plugin<Project> {

    void apply(Project project) {

        System.out.println("===========plugin init===========")
        def android=project.extensions.getByType(AppExtension)
        android.registerTransform(new MyTransform())
    }
}