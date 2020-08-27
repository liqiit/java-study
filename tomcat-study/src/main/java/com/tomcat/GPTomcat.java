package com.tomcat;

import com.tomcat.http.GPRequest;
import com.tomcat.http.GPResponse;
import com.tomcat.servlet.Servlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {
    private int port = 8080;
    private Map<String, Servlet> servletMap = new HashMap<>();
    private Properties properties = new Properties();
    private ServerSocket serverSocket;

    private void init() throws Exception {
        String WEB_INF = this.getClass().getResource("/").getPath();
        FileInputStream fileInputStream = new FileInputStream(WEB_INF + "web.properties");
        properties.load(fileInputStream);
        properties.entrySet().forEach(entry -> {
            String className = String.valueOf(entry.getKey());
            Servlet servlet = null;
            try {
                servlet = (Servlet) (Class.forName(className).newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            servletMap.put(String.valueOf(entry.getValue()), servlet);
        });
    }

    public void start() throws Exception {
        init();
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket client = serverSocket.accept();
            process(client);
        }

    }

    private void process(Socket client) {
        try {
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            GPRequest request = new GPRequest(inputStream);
            GPResponse response = new GPResponse(outputStream);

            String url = request.getUrl();
            Servlet servlet = servletMap.get(url);
            if (servlet == null) {
                response.write("404 - Not Found");
                return;
            }
            servlet.service(request, response);

            outputStream.flush();
            outputStream.close();

            inputStream.close();

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            GPTomcat tomcat = new GPTomcat();
            tomcat.start();
            System.out.println(tomcat.servletMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
