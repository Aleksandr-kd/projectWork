package org.data;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"org.data.factory", "org.data.pages"})
public class TestApplication {
    public static void main(String[] args) {
    }
}