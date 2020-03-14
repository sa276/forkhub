package com.example.android.gma;

public class global_test {
    public static String username,password;

    public static Boolean test_username(String username){
        if(username.length()>4 && usernamecheck(username)==true){
            return true;
        }
        return false;
    }

    public static Boolean test_password(String password){
        if(password.length()>0){
            return true;
        }
        return false;
    }

    public static Boolean usernamecheck(String username){
        if(username.substring(0,4).equals("GMA.")||username.substring(0,3).equals("ADV.")||
                username.substring(0,4).equals("SUB.")||username.substring(0,3).equals("CM.")
                ||username.substring(0,3).equals("EC.")){
            return true;
        }
        else
            return false;
    }


}
