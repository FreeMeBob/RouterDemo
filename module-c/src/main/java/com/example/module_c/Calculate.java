package com.example.module_c;
import com.example.my_annotation.Module;

/**
 * Created by xinmei on 2018/1/25.
 */
@Module
public class Calculate {

    public double add(double x,double y){

        return x+y;
    }
    public double power(double x,int y){

        return Math.pow(x,y);
    }

}
