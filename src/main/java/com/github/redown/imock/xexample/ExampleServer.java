package com.github.redown.imock.xexample;

import com.github.redown.imock.api.IApiScan;
import com.github.redown.imock.mock.IMockServer;
import com.github.redown.imock.mock.MockServer;
import com.github.redown.imock.xexample.handler.TimeServerHandler;

/**
 * @author redown
 */
@IApiScan(service={"com.github.redown.imock.xexample.service"})
@IMockServer(port = 9381, hander = TimeServerHandler.class)
public class ExampleServer {
    public static void main(String[] args) {
        MockServer.run(ExampleServer.class, args);
    }

}
