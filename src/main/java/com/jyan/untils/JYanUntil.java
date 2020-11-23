package com.jyan.untils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class JYanUntil {
    static boolean flag=true;
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static Timestamp getTime(){
        return new Timestamp(new Date().getTime());
    }
    public static void print(String msg){
        if(flag){
            System.out.println("JYan:=>"+msg);
        }
    }
}
