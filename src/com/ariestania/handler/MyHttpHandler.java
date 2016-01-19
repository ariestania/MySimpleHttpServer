/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ariestania.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ariestania.winda
 */
public class MyHttpHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        String resp = "<h1>Hi .. You are on Bohay Test Http Server</h1>";
        he.sendResponseHeaders(200, resp.length());
        try (OutputStream os = he.getResponseBody()) {
            os.write(resp.getBytes());
        }
    }
    
}
