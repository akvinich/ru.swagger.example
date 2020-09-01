package ru.open.swagger.dto;

import java.util.Collections;

import static ru.open.swagger.helpers.PropHelper.*;
import static ru.open.swagger.helpers.PropHelper.EMAIL_OWNER;

public class TaskPojo extends BasePojo {

    public BasePojo getNewTaskByPartName(String taskName) {
        jsonBody.clear();
        return this
                .withProp(DESCRIPTION, "New-" + taskName + "-" + System.nanoTime())
                .withProp(TITLE, taskName + System.nanoTime());
    }
}
