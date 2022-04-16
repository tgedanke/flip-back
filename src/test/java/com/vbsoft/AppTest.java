package com.vbsoft;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test() {
        System.out.println(this.t());
        System.out.println("g");
    }

    public String t() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> System.out.println("aaaa"), 1,  TimeUnit.SECONDS);
        return "b";
    }

}
