package com.github.redown.imock.api;

import java.util.List;

/**
 * @author redown
 */
public class Api {
    private String apiId;
    private String apiName;
    private String apiMethod;
    private List<ApiField> apiInputs;
    private List<ApiField> apiOutputs;

    public Api(String apiId, String apiName, String apiMethod, List<ApiField> apiInputs, List<ApiField> apiOutputs) {
        this.apiId = apiId;
        this.apiName = apiName;
        this.apiMethod = apiMethod;
        this.apiInputs = apiInputs;
        this.apiOutputs = apiOutputs;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public List<ApiField> getApiInputs() {
        return apiInputs;
    }

    public void setApiInputs(List<ApiField> apiInputs) {
        this.apiInputs = apiInputs;
    }

    public List<ApiField> getApiOutputs() {
        return apiOutputs;
    }

    public void setApiOutputs(List<ApiField> apiOutputs) {
        this.apiOutputs = apiOutputs;
    }

    @Override
    public String toString() {
        return "Api{" +
                "apiId='" + apiId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", apiMethod='" + apiMethod + '\'' +
                ", apiInputs=" + apiInputs +
                ", apiOutputs=" + apiOutputs +
                '}';
    }
}
