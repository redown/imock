package com.github.redown.imock.mock;

import com.github.redown.imock.api.Api;

import java.util.List;

/**
 * @author redown
 */
public interface IMockServerContext {
    public void addApi(Api api);
    public void addApi(List<Api> apis);
}
