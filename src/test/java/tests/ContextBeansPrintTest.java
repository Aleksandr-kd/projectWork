package tests;

import org.data.TestApplication;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest(classes = TestApplication.class)
public class ContextBeansPrintTest {

    @Autowired
    private ApplicationContext context;

    @Tag("1")
    @Test
    void printAllBeans() {
        Arrays.stream(context.getBeanDefinitionNames())
                .sorted()
                .forEach(System.out::println);
    }
}