/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ariestania.server;

import com.ariestania.handler.MyHttpEchoHeaderHandler;
import com.ariestania.handler.MyHttpGetHandler;
import com.ariestania.handler.MyHttpHandler;
import com.ariestania.handler.MyHttpPostHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariestania.winda
 */
public class MyServer {

    public MyServer() {
        int port = 3000;
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 10);
            System.out.println("Server start at port : " + port);
            server.createContext("/bohaytest", new MyHttpHandler());
            server.createContext("/bohaypost", new MyHttpPostHandler());
            server.createContext("/bohayget", new MyHttpGetHandler());
            server.createContext("/bohayecho", new MyHttpEchoHeaderHandler());
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20, true), new RejectedHandler());
            server.setExecutor(executor);
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(MyHttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class RejectedHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            r.run();
        }

    }
}
