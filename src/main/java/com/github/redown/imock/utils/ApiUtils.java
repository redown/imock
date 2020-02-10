package com.github.redown.imock.utils;

import com.github.redown.imock.api.*;
import com.github.redown.imock.api.IApiScan;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author redown
 */
public class ApiUtils {
    public static List<Api> apiScan(Class<?> serverClass) {
        List<Api> apiList = new ArrayList<>();
        IApiScan iApiScan = serverClass.getDeclaredAnnotation(IApiScan.class);
        if (iApiScan == null) {
            return null;
        }
        String rootPath = serverClass.getResource("/").getPath();
        String[] packages = iApiScan.service();
        Set<Class<?>> classSet = ClassUtils.parseClass(packages);
        for (Class<?> c: classSet) {
            if (isApi(c)) {
                apiList.addAll(parseApi(c));
            }
        }
        return apiList;
    }

    private static List<Api> parseApi(Class<?> apiClass) {
        List<Api> apiList = new ArrayList<>();
        String apiId = null;
        String apiName = null;
        String apiMethod = null;
        List<ApiField> apiInputs = null;
        List<ApiField> apiOutputs = null;

        IApi iApi = apiClass.getDeclaredAnnotation(IApi.class);
        Method[] methods = apiClass.getDeclaredMethods();
        for (Method method : methods) {
            if (!isMethod(method)) {
                continue;
            }
            IApiMethod iApiMethod = method.getDeclaredAnnotation(IApiMethod.class);
            apiId = StringUtils.isEmpry(iApiMethod.id()) ? method.getName() : iApiMethod.id();
            apiName = StringUtils.isEmpry(iApiMethod.name()) ? method.getName() : iApiMethod.name();
            apiMethod = StringUtils.isEmpry(iApiMethod.alias()) ? method.getName() : iApiMethod.alias();
            if (method.getParameterTypes().length == 0) {
                continue;
            }
            Class<?> inputClass = method.getParameterTypes()[0];
            apiInputs = parseEntity(inputClass);
            Class<?> outputClass = method.getReturnType();
            apiOutputs = parseEntity(outputClass);
            apiList.add(new Api(apiId, apiName, apiMethod, apiInputs, apiOutputs));
        }
        return apiList;
    }

    private static List<ApiField> parseEntity(Class<?> entityClass) {
        List<ApiField> apiFieldList = new ArrayList<>();
        IApiEntity iApiInput = entityClass.getDeclaredAnnotation(IApiEntity.class);
        Field[] inputFields = entityClass.getDeclaredFields();
        for (Field field: inputFields) {
            if (isField(field)) {
                IApiField field1 = field.getDeclaredAnnotation(IApiField.class);
                String key = field1.alias() == null ? field.getName(): field1.alias();
                String name = field1.name() == null ? field.getName(): field1.name();
                apiFieldList.add(new ApiField(key, name, field1.value(), field1.type(), field1.length(), field1.alias(), field1.rule(), field1.range()));
            } else {
                apiFieldList.add(new ApiField(field.getName(), field.getName()));
            }
        }
        return apiFieldList;
    }

    private static boolean isApi(Class<?> apiClass) {
        return apiClass.getDeclaredAnnotation(IApi.class) != null;
    }

    private static boolean isMethod(Method method) {
        return method.getDeclaredAnnotation(IApiMethod.class) != null;
    }

    private static boolean isField(Field field) {
        return field.getDeclaredAnnotation(IApiField.class) != null;
    }
}
