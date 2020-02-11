package com.github.redown.imock.xexample;

import com.github.redown.imock.api.IApiScan;
import com.github.redown.imock.mock.IMockServer;
import com.github.redown.imock.mock.IMockServerType;
import com.github.redown.imock.mock.MockServer;
import com.github.redown.imock.xexample.handler.HttpServerHandler;
import com.github.redown.imock.xexample.service.PersonService;

/**
 * @author redown
 */
@IApiScan(service={"com.github.redown.imock.xexample.service"})
@IMockServer(type = IMockServerType.HTTP, port = 9381, hander = HttpServerHandler.class)
public class ExampleServer {
    public static void main(String[] args) {
        MockServer.run(ExampleServer.class, args);
    }

}
