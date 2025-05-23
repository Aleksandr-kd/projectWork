package org.data.factory;

import org.data.exceptions.BrowserNotSupportedException;
import org.data.factory.settings.ChromeSettings;
import org.data.factory.settings.FirefoxSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;



@Configuration
public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    @Bean(name = "testDriver")
    @Primary
    @Scope("prototype")
    public WebDriver getDriver() {
        WebDriver driver = createDriver();
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        return driver;
    }

    public WebDriver createDriver() {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            case "firefox" -> new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            default -> throw new BrowserNotSupportedException(browserName);
        };
    }
}
