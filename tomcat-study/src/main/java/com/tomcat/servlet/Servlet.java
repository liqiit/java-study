package com.tomcat.servlet;

import com.tomcat.http.GPRequest;
import com.tomcat.http.GPResponse;

public abstract class Servlet {
    public void service(GPRequest request, GPResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(GPRequest request, GPResponse response) throws Exception;

    protected abstract void doGet(GPRequest request, GPResponse response) throws Exception;
}
