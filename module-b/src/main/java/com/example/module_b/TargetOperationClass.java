package com.example.module_b;

import com.example.module_c.Calculate;
import com.example.my_annotation.AutoWire;
import com.example.my_annotation.Module;

/**
 * Created by xinmei on 2018/1/25.
 */
@Module
public class TargetOperationClass {

    @AutoWire
    public void testMethod1() {

    System.out.print("This is testMethod1");
    }

    @AutoWire
    public void testMethod2() {

    System.out.println("This is testMethod2");
    }

}
