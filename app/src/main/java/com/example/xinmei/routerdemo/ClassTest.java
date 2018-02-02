package com.example.xinmei.routerdemo;

import com.example.module_b.TargetOperationClass;
import com.example.module_c.Calculate;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xinmei on 2018/1/25.
 */

public class ClassTest {

    public void myTest() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        System.out.println("============= This is RouterDemo ============");
        TargetOperationClass targetOperationClass=new TargetOperationClass();

        Object calculator=Util.getCalculator();

        targetOperationClass.testMethod1();
        targetOperationClass.operation();

        Calculate calculate=new Calculate();
        System.out.println(calculate.add(1,2));
        System.out.println(calculate.power(2.0,2));

    }
}
