package com.github.redown.imock.xexample;

import com.github.redown.imock.mock.IMockApplication;
import com.github.redown.imock.mock.MockApplication;

/**
 * @author redown
 */
@IMockApplication
public class ExampleApplication {
    public static void main(String[] args) {
        MockApplication.run(ExampleApplication.class, args);
    }
}
