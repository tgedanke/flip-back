package com.vbsoft;

import com.vbsoft.Services.SamsungTrackService;
import com.vbsoft.Services.scheduled.SamsungASKANSSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Root application class.
 */
@SpringBootApplication
@EnableScheduling
public class App
{
    /**
     * Main method.
     * @param args Application arguments
     */
    public static void main( String[] args )
    {
        ConfigurableApplicationContext application = SpringApplication.run(App.class, args);
        //application.getBean(SamsungTrackService.class).start();
    }

}
