package com.flippost.Tools.Statistic;

import com.flippost.Tools.RuleEngine.RuleEngine;
import com.flippost.Tools.Tools.FlippostTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class FlippostStatistic {

    private FlippostStatistic() {
        // do nothing
    }

    public static void addError(final String MESSAGE) {
        addError(MESSAGE, null);
    }

    public static void addError(final String MESSAGE, final Throwable throwable) {

        final Date date = new Date();
        SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.yyyy H:m:s");
        String content =
                pattern.format(date)
                        + " [ERROR] "
                        + MESSAGE
                        + "\r\n";
        if(throwable != null) {
            StringBuilder stackTrace = new StringBuilder();
            stackTrace.append("StackTrace: \r\n");
            Arrays.asList(throwable.getStackTrace()).forEach(stack ->
                    stackTrace.append(stack).append("\r\n"));
            content += throwable.getMessage()
                    + "\r\n"
                    + stackTrace.toString();
        }

        String pathToSave = RuleEngine.workPathRule()
                + "\\Flippost"
                + "\\Errors";
        final File PATH = new File(pathToSave);
        if(!PATH.exists())
            PATH.mkdirs();

        pathToSave += "\\Errors.log";
        saveToFile(pathToSave, content);


    }

    private static void saveToFile(final String FILE_PATH,
                                   final String CONTENT) {

        try {
            FlippostTools.saveToFile(FILE_PATH, CONTENT);
        } catch (IOException e) {
            System.out.println("Не удалось записать статистику");
            e.printStackTrace();
        }
    }




}
