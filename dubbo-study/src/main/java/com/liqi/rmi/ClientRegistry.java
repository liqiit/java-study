package com.liqi.rmi;

import com.liqi.service.HelloService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Title: ClientRegistry
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/3
 */
public class ClientRegistry {
    public static void main(String[] args) {
        try {
            String json="{\"imsi\":454061110000101,\"msisdn\":\"8010010000119\",\"iccid\":\"89801001000000001192\",\"usage\":[{\"used_traffic\":0,\"usage_start_time\":\"\",\"usage_end_time\":\"\",\"plan_name\":\"10 Days 200 MB Data Pack\",\"product_code\":\"IF_GEN_10DAY_200MB\",\"order_number\":\"722af4859a0b4ec7b7ff5194478459df\",\"subscription_time\":\"2020-09-04T08:15:50.417Z\",\"subscription_status\":\"NEW\"},{\"used_traffic\":1630381,\"usage_start_time\":\"2020-09-04T07:29:17.000Z\",\"usage_end_time\":\"2020-09-05T07:26:17.000Z\",\"plan_name\":\"10 Days 200 MB Data Pack\",\"product_code\":\"IF_GEN_10DAY_200MB\",\"order_number\":\"702a82be94c44f94857b047432dcc9cb\",\"subscription_time\":\"2020-09-04T07:26:17.708Z\",\"subscription_status\":\"ACTIVATED\"}],\"shared_group_id\":null}\n";
           Registry registry= LocateRegistry.getRegistry();
           HelloService helloService=(HelloService)registry.lookup("hello-service");
           String message=helloService.sayHello("liqi");
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
