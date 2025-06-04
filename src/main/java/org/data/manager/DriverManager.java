package org.data.manager;


import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;


@Component
public class DriverManager {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}