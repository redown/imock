package com.github.redown.imock.mock;

/**
 * @author redown
 */
public class MockApplication {

    public MockApplication(Class<?> applicationClass) {
        System.out.println("New Mock ExampleApplication");
    }

    public static IMockApplicationContext run(Class<?> applicationClass, String[] args) {
        return (new MockApplication(applicationClass)).run(args);
    }

    private IMockApplicationContext run(String[] args) {
        IMockApplicationContext context = null;
        try {
            context = IMockApplicationContext.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return context;
    }
}
