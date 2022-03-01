package com.vbsoft;

import com.vbsoft.Services.SamsungTrackService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Root application class.
 */
@SpringBootApplication
public class App
{
    /**
     * Main method.
     * @param args Application arguments
     */
    public static void main( String[] args )
    {
        ConfigurableApplicationContext application = SpringApplication.run(App.class, args);

        application.getBean(SamsungTrackService.class).start();
    }

}
