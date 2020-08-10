package ru.open.swagger;

import com.google.common.primitives.Ints;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.slf4j.MDC;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Optional;

public class ServiceTest {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @BeforeMethod(alwaysRun = true)
    protected void setupLogStamp(Method method) {
        Optional<Story> allureStory = Optional.ofNullable(method.getAnnotation(Story.class));
        Integer testIdValue = allureStory
                .map(Story::value)
                .map(value -> value.split("\\s")[0])
                .map(Ints::tryParse)
                .orElse(null);
        String stamp = String.format("TestID=%d", testIdValue);
        MDC.put("TestID", stamp);
    }

}
