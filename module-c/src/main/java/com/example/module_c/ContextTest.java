package com.example.module_c;

import android.content.Context;

/**
 * Created by Bob on 2018/2/28.
 */

public class ContextTest {
    Context context;
    public ContextTest(Context context){
        this.context=context;
    }
    public void isWorkable(){
        if(context==null){
            System.out.println("Context is null, fail");
        }
        else{
            System.out.println("succeed");
        }
    }

}
