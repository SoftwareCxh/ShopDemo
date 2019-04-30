package com.example.graduationproject.base;

public class Constant {
    public static boolean login=false;
    public static String username;
    public static String name;


    public static void loginSuc(String username){
        login=true;
        Constant.username=username;
    }


}
