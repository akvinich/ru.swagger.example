package ru.open.swagger;

import com.google.common.primitives.Ints;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.slf4j.MDC;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.open.swagger.dto.DoRegisterPojo;
import ru.open.swagger.steps.Steps;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class UsersTest {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    public final static String BASE_URL = "http://users.bugred.ru/tasks/rest";
    public final static String ENDPOINT_DO_REGISTER = "/doRegister";
    public final static String ENDPOINT_CREATE_COMPANY = "/createcompany";
    public final static String ENDPOINT_CREATE_USER = "/createuser";
    public final static String ENDPOINT_CREATE_USER_WITH_TASKS = "/createuserwithtasks";
    public final static String ENDPOINT_ADD_AVATAR = "/addavatar";
    public final static String ENDPOINT_MAGIC_SEARCH = "/magicsearch";

    private Steps steps = new Steps(BASE_URL);
    private DoRegisterPojo doRegisterPojo = new DoRegisterPojo();

    public UsersTest() {}

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

    @Epic("Smoke")
    @Story("Users-100")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000)
    public void checkDoRegisterNewUser() {
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.getNewUserRegisterPojo().getJsonBody());
    }

    @Epic("Smoke")
    @Story("Users-101")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000)
    public void checkDoRegisterEmailError() {
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.expectExistEmailErrorRegisterPojo().getJsonBody());
    }

    @Epic("Smoke")
    @Story("Users-102")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000)
    public void checkDoRegisterNameError() {
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.expectExistNameErrorRegisterPojo().getJsonBody());
    }

}
