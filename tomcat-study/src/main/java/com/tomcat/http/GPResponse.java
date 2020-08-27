package com.tomcat.http;

import java.io.OutputStream;

public class GPResponse {
    private OutputStream outputStream;

    public GPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String s) throws Exception {
        StringBuffer response = new StringBuffer();
        response.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:text/html;\n")
                .append("\r\n")
                .append(s);
        outputStream.write(response.toString().getBytes("utf-8"));
        outputStream.close();
    }


}
