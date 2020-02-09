package com.github.redown.imock.example;

import com.github.redown.imock.common.IMockApplication;
import com.github.redown.imock.common.MockApplication;

/**
 * @Author redown
 * @Date 2020/2/7 0007 18:34
 * @Description
 */
@IMockApplication
public class ExampleApplication {
    public static void main(String[] args) {
        MockApplication.run(ExampleApplication.class, args);
    }
}
