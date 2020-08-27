package com.flippost.Tools.RuleEngine;

import java.io.File;

public class RuleEngine {

    private RuleEngine() {
        //Do nothing
    }

    public static String workPathRule() {
        String workPath = System.getProperty("work.path");
        workPath =
                (workPath == null || workPath.length() == 0)
                ? "C:\\"
                : workPath;
        return workPath;
    }

    public static String hibernateFileRule() {
        final String MODE = System.getProperty("work.mode").toUpperCase();
        String result;
        switch (MODE) {
            default :
                    result = "hibernate.hbm.xml";
            break;
            case "TEST" :
            case "DEBUG" :
                result = "hibernate.test.hbm.xml";
            break;
            case "LOCAL":
                result = "hibernate.local.hbm.xml";
            break;
        }
        return result;
    }

}
