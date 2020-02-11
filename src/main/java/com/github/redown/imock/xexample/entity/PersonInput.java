package com.github.redown.imock.xexample.entity;

import com.github.redown.imock.api.IApiField;
import com.github.redown.imock.api.IApiEntity;

/**
 * @author redown
 */

@IApiEntity(name = "Person Input")
public class PersonInput {

    @IApiField(name="Name", length = {8, 16}, alias = "userName")
    String name;

    @IApiField(name = "Age", range = {18, 60})
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
