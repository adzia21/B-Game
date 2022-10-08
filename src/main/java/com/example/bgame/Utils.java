package com.example.bgame;

public class Utils {
    public static String removeHtmlCode(String text){
        return text.replaceAll("<.*?>", "");
    }
}
