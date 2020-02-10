package com.github.redown.imock.utils;

/**
 * @author redown
 */
public class StringUtils {
    public static boolean isEmpry(String var) {
        return var == null || var.length() == 0;
    }

    public static boolean areNotEmpty(String var) {
        return !isEmpry(var);
    }
}
