/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ariestania.handler;

import com.ariestania.util.MyUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ariestania.winda
 */
public class MyHttpGetHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // parse request
        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        MyUtil.parseQuery(query, parameters);

        // send response
        String response = "";
        for (String key : parameters.keySet()) {
            response += key + " = " + parameters.get(key) + "\n";
        }
        he.sendResponseHeaders(200, response.length());
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.toString().getBytes());
        }
    }

}
