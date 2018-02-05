package com.example.module_b;

import com.example.module_c.Calculate;
import com.example.my_annotation.AutoWire;
import com.example.my_annotation.Module;


/**
 * Created by xinmei on 2018/1/25.
 */
@Module
public class TargetOperationClass {


    Object object;
    int num=0;
    public TargetOperationClass(){

    }

    public void testMethod1() {

    System.out.println("This is testMethod1");
    }
@AutoWire
    public void operation(Object object) throws IllegalAccessException, InstantiationException {

//        Object object=mClass.newInstance();
//        Method [] methods=mClass.getMethods();
//        for(Method m:methods){
//            System.out.print(m);
//        }
//
//        Method m1=mClass.getMethod("add",double.class,double.class);
//        double d1=(double)m1.invoke(object,2.0,2.0);
//        System.out.println("========= "+d1+" ===========");
//        Method m2=mClass.getMethod("power",double.class,int.class);
//        double d2=(double)m2.invoke(object,2.0,2);
            //TODO： 实例化 object 通过A方法 Util
        //object=mClass.newInstance(); code to be injected
        // this.object=object
        System.out.println("***************** " + object);

    }
}
