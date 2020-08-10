package ru.open.swagger;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ru.open.swagger.dto.DoRegisterPojo;
import ru.open.swagger.steps.Steps;

import java.util.HashMap;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class UsersTest extends ServiceTest {

    public final static String BASE_URL = "http://users.bugred.ru/tasks/rest";
    public final static String ENDPOINT_DO_REGISTER = "/doRegister";
    public final static String ENDPOINT_CREATE_COMPANY = "/createcompany";
    public final static String ENDPOINT_CREATE_USER = "/createuser";
    public final static String ENDPOINT_CREATE_USER_WITH_TASKS = "/createuserwithtasks";
    public final static String ENDPOINT_ADD_AVATAR = "/addavatar";
    public final static String ENDPOINT_MAGIC_SEARCH = "/magicsearch";

    private Steps steps = new Steps(BASE_URL);
    private DoRegisterPojo doRegisterPojo = new DoRegisterPojo();

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
