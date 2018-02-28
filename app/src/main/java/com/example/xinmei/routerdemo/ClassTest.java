package com.example.xinmei.routerdemo;

import android.app.Activity;
import android.content.Context;

import com.example.module_b.TargetOperationClass;
import com.example.module_c.Calculate;
import com.example.module_c.ContextTest;
import com.example.module_c.Parse;
import com.example.my_annotation.AutoWire;
import com.example.my_annotation.AutoWirePrep;
import com.example.my_annotation.Module;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xinmei on 2018/1/25.
 */
@Module
public class ClassTest {
@AutoWirePrep
    public void myTest(Context context) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        System.out.println("============= This is RouterDemo ============");
        TargetOperationClass targetOperationClass=new TargetOperationClass();
        targetOperationClass.testMethod1();
        targetOperationClass.operation();

       /* Object calculator=Util.getCalculator();
        targetOperationClass.operation(calculator);*/

        Calculate calculate=new Calculate();
        System.out.println(calculate.add(1,2));
        System.out.println(calculate.power(2.0,2));
        //ContextTest contextTest=new ContextTest(context);
        //contextTest.isWorkable();
    }
}
