package com.github.redown.imock.api;

import sun.security.util.Length;

import java.util.Arrays;

/**
 * @author redown
 */
public class ApiField {
    private String key;
    private String name;
    private String value;
    private Class<?> type;
    private int[] length;
    private String alias;
    private String rule;
    private int[] range;

    public ApiField(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public ApiField(String key, String name, String value, Class<?> type, int[] length, String alias, String rule, int[] range) {
        this.key = key;
        this.name = name;
        this.value = value;
        this.type = type;
        this.length = length;
        this.alias = alias;
        this.rule = rule;
        this.range = range;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public int[] getLength() {
        return length;
    }

    public void setLength(int[] length) {
        this.length = length;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int[] getRange() {
        return range;
    }

    public void setRange(int[] range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "ApiField{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                ", length=" + Arrays.toString(length) +
                ", alias='" + alias + '\'' +
                ", rule='" + rule + '\'' +
                ", range=" + Arrays.toString(range) +
                '}';
    }
}
