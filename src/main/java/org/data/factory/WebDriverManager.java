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
import org.springframework.context.annotation.Scope;


@Configuration
public class WebDriverManager {

    @Bean
    @Scope("prototype")
    public WebDriver testDriver() {
        String browserName = System.getProperty("browser");
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            case "firefox" -> new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            default -> throw new BrowserNotSupportedException(browserName);
        };
    }
}
