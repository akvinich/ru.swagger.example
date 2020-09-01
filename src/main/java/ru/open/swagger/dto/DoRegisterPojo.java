package ru.open.swagger.dto;

import static ru.open.swagger.helpers.PropHelper.*;

public class DoRegisterPojo extends BasePojo {

    public BasePojo expectExistEmailErrorRegisterPojo() {
        jsonBody.clear();
        return this
                .withProp(EMAIL, "akulis@gmail.com")
                .withProp(NAME, "villi")
                .withProp(PASSWORD, "4545454545");
    }

    public BasePojo expectExistNameErrorRegisterPojo() {
        jsonBody.clear();
        return this
                .withProp(EMAIL, "akulis" + System.nanoTime() + "@gmail.com")
                .withProp(NAME, "villi")
                .withProp(PASSWORD, "4545454545");
    }

    public BasePojo getNewUserRegisterPojo() {
        jsonBody.clear();
        return this
                .withProp(EMAIL, "currentTimeEmail" + System.nanoTime() + "@gmail.com")
                .withProp(NAME, "villi" + + System.nanoTime())
                .withProp(PASSWORD, "4545454545");
    }
}
