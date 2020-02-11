package com.github.redown.imock.xexample.entity;

import com.github.redown.imock.api.IApiEntity;

/**
 * @author redown
 */

@IApiEntity(name = "Person Output")
public class PersonOutput {
    String name;
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
