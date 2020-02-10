package com.github.redown.imock.utils;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author redown
 */
public class ClassUtils {
    public static Set<Class<?>> parseClass(String[] packages) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        for (String packageName: packages) {
            classes.addAll(parseClass(packageName));
        }
        return classes;
    }

    public static Set<Class<?>> parseClass(String packageName) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        try {
            String rootPath = Thread.currentThread().getClass().getResource("/").getPath();
            String classPath = rootPath + packageName.replace(".", "/");
            File file = new File(classPath);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.getName().endsWith(".class")) {
                        try {
                            Class<?> c = Class.forName(packageName + "." + f.getName().replace(".class", ""));
                            classes.add(c);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
}
