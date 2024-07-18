package com.amatsolutions.samples.taskmanager.util;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChromeDriverUtils {
    private boolean headlessEnable=true;
    private String chromeBynaryPath;
    private boolean backup=true;
    private boolean notSwitchWindow=true;

    @Autowired
    private Environment environment;

    public ChromeDriver initializeDriver() {



        if (headlessEnable) {
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless"); //!!!should be enabled for Jenkins

            options.addArguments("--disable-dev-shm-usage"); //!!!should be enabled for Jenkins
            options.addArguments("--window-size=1920x1080"); //!!!should be enabled for Jenkins
            options.addArguments("--no-sandbox");
            chromeBynaryPath = environment.getProperty("service.driver.path");
            options.setBinary(chromeBynaryPath);
            if (backup) {
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
                        + "AppleWebKit/537.36 (KHTML, like Gecko)"
                        + "Chrome/106.0.5249.119 Safari/537.36");
            }
            ChromeDriver driver = new ChromeDriver(options);
            notSwitchWindow = false;
            return driver;
        } else {
            notSwitchWindow = false;
            ChromeDriver driver = new ChromeDriver();
            return driver;
        }

    }
}
