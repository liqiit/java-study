package com.liqi;

import com.liqi.pojo.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoogleMapDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoogleMapDemoApplication.class, args);
    }
    @Bean(name ="aa" )
    public Address getAddress(){
        return new Address();
    }
}
