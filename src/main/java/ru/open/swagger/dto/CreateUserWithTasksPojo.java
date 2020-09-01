package ru.open.swagger.dto;

import java.util.Collections;

import static ru.open.swagger.helpers.PropHelper.*;

public class CreateUserWithTasksPojo extends BasePojo {

    private TaskPojo taskPojo = new TaskPojo();

    public BasePojo getNewUserPojo(String taskName) {
        jsonBody.clear();
        return this
                .withProp(EMAIL, "currentTimeEmail" + System.nanoTime() + "@gmail.com")
                .withProp(NAME, "villi" + + System.nanoTime())
                .withProp(TASKS, Collections.singletonList(taskPojo.getNewTaskByPartName(taskName).getJsonBody()));
    }
}
