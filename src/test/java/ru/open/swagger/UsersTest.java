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
import ru.open.swagger.dto.CreateCompanyPojo;
import ru.open.swagger.dto.CreateUserWithTasksPojo;
import ru.open.swagger.dto.DoRegisterPojo;
import ru.open.swagger.steps.Steps;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Optional;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static ru.open.swagger.helpers.PropHelper.*;

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
    public final static String NEW_USER_EMAIL = "owner" + System.nanoTime() + "@gmail.com";

    private Steps steps = new Steps(BASE_URL);
    private DoRegisterPojo doRegisterPojo = new DoRegisterPojo();
    private CreateCompanyPojo createCompanyPojo = new CreateCompanyPojo();
    private CreateUserWithTasksPojo createUserWithTasksPojo = new CreateUserWithTasksPojo();

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
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.getNewUserRegisterPojo()
                .withProp(EMAIL, NEW_USER_EMAIL).getJsonBody());
    }

    @Epic("Smoke")
    @Story("Users-101")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000, dependsOnMethods = {"checkDoRegisterNewUser"})
    public void checkDoRegisterEmailError() {
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.expectExistEmailErrorRegisterPojo()
                .withProp(EMAIL, NEW_USER_EMAIL).getJsonBody());
    }

    @Epic("Smoke")
    @Story("Users-102")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000, dependsOnMethods = {"checkDoRegisterNewUser"})
    public void checkDoRegisterNameError() {
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.expectExistNameErrorRegisterPojo().getJsonBody());
    }

    @Epic("Smoke")
    @Story("Users-103")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000, dependsOnMethods = {"checkDoRegisterNewUser"})
    public void checkCreateCompany() {
        String newEmployerEmail = "employer" + System.nanoTime() + "@gmail.com";
        steps.httpPost(ENDPOINT_DO_REGISTER, doRegisterPojo.getNewUserRegisterPojo()
                .withProp(EMAIL, newEmployerEmail).getJsonBody());
        steps.httpPost(ENDPOINT_CREATE_COMPANY, createCompanyPojo.expectSuccessAddedNewCompanyPojo()
                .withProp(EMAIL_OWNER, NEW_USER_EMAIL)
                .withProp(COMPANY_USERS, Collections.singletonList(newEmployerEmail))
                .getJsonBody());
    }

    @Epic("Smoke")
    @Story("Users-104")
    @Feature("Swagger-coverage")
    @Severity(CRITICAL)
    @Test(timeOut = 30000, dependsOnMethods = {"checkDoRegisterNewUser"})
    public void checkCreateUserWithTasks() {
        steps.httpPost(ENDPOINT_CREATE_USER_WITH_TASKS, createUserWithTasksPojo
                .getNewUserPojo("UserWithTask").getJsonBody());
    }
}
