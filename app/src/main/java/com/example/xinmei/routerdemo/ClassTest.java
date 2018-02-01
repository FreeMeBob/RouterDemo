package com.example.xinmei.routerdemo;

import com.example.module_b.TargetOperationClass;
import com.example.module_c.Calculate;

/**
 * Created by xinmei on 2018/1/25.
 */

public class ClassTest {

    public static void main(String args[]){

        System.out.print("This is RouterDemo");
        TargetOperationClass targetOperationClass=new TargetOperationClass();
        targetOperationClass.testMethod1();
        targetOperationClass.testMethod2();
        Calculate calculate=new Calculate();
        System.out.println(calculate.add(1,2));
        System.out.println(calculate.power(2.0,2));

    }
}
