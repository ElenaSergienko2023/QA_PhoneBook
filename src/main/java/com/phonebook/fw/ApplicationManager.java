package com.phonebook.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class ApplicationManager {

    String browser;
    public WebDriver driver;

    UserHelper user;
    ContactHelper contact;
    HomePageHelper homePage;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        System.err.close();

        if (browser.equalsIgnoreCase("chrome")) {
            driver =new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
//            FirefoxOptions options = new FirefoxOptions();
//            options.addArguments("remote-allow-origins=*");
//            driver = new FirefoxDriver(options);
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
//            EdgeOptions options = new EdgeOptions();
//            options.addArguments("remote-allow-origins=*");
//            driver = new EdgeDriver(options);
            driver = new EdgeDriver();
        }

//      driver = new ChromeDriver();
        driver.get("https://telranedu.web.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomePageHelper getHomePage() {
        return homePage;
    }

    public void stop() {
        driver.quit();
    }
}
