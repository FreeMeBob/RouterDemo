package com.example.module_b;

import com.example.module_c.Calculate;
import com.example.my_annotation.AutoWire;
import com.example.my_annotation.Module;

import java.lang.reflect.Method;


/**
 * Created by xinmei on 2018/1/25.
 */
@Module
public class TargetOperationClass {

    Calculate object;
    int num = 0;

    public TargetOperationClass() {
        /*try {
            Class utilClass = Class.forName("com.example.xinmei.routerdemo.Util");
            Method utilMethod = utilClass.getMethod("getCalculator", null);
            this.object = utilMethod.invoke(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    public void testMethod1() {

        System.out.println("This is testMethod1");
    }

    @AutoWire
    public void operation() throws IllegalAccessException, InstantiationException {

        System.out.println("===========" + object);
    }
}
