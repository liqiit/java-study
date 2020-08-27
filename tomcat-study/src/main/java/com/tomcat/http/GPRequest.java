package com.tomcat.http;

import java.io.IOException;
import java.io.InputStream;

public class GPRequest {
    private String url;
    private String method;

    public GPRequest(InputStream inputStream) {
        String content = "";
        byte[] bytes = new byte[1024];
        int length;
        try {
            if ((length = inputStream.read(bytes)) != -1) {
                content = new String(bytes, 0, length);
            }
            doParse(content);
            System.out.println("请求：" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doParse(String request) {
        String[] lines = request.split("\n");
        int start = lines[0].indexOf(" ");
        method = lines[0].substring(0, start);
        int end = lines[0].lastIndexOf(" ");
        url = lines[0].substring(start + 1, end);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
