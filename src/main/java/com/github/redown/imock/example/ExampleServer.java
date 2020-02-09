package com.github.redown.imock.example;

import com.github.redown.imock.common.IMockServer;
import com.github.redown.imock.common.MockServer;
import com.github.redown.imock.service.time.TimeServerHandler;

/**
 * @Author redown
 * @Date 2020/2/9 0009 10:13
 * @Description
 */
@IMockServer(port = 9381, hander = TimeServerHandler.class)
public class ExampleServer {
    public static void main(String[] args) {
        MockServer.run(ExampleServer.class, args);
    }

}
