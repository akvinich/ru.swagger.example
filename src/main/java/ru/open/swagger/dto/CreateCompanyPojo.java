package ru.open.swagger.dto;

import java.util.Collections;

import static ru.open.swagger.helpers.PropHelper.*;

public class CreateCompanyPojo extends BasePojo {

    public BasePojo expectSuccessAddedNewCompanyPojo() {
        jsonBody.clear();
        return this
                .withProp(COMPANY_NAME, "New Villi's Company")
                .withProp(COMPANY_TYPE, "ОАО")
                .withProp(COMPANY_USERS,
                        Collections.singletonList("currentTimeEmail" + System.nanoTime() + "@gmail.com"))
                .withProp(EMAIL_OWNER, "owner" + System.nanoTime() + "@gmail.com");
    }
}
