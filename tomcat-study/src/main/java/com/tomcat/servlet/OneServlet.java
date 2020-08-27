package com.tomcat.servlet;


import com.tomcat.http.GPResponse;
import com.tomcat.http.GPRequest;

public class OneServlet extends Servlet {
    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        doGet(request, response);
    }

    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        response.write("this is OneServlet");
    }
}
