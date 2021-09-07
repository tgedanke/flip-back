package com.vbsoft;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test() throws IOException {
        System.out.println(Files.createTempDirectory("temp").toFile().getAbsolutePath());
    }

    @Test
    public void t() {
        Set<Integer> set1 = Set.of(1, 2, 3);
        Set<Integer> set2 = Set.of(0, 1, 2);
        Set<Integer> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);

        Iterator<Integer> it = result.iterator();
        while (it.hasNext()) {
            int temp;
            if(set2.contains(temp = it.next()) && set1.contains(temp))
                it.remove();
        }

        System.out.println(result);
    }

}
