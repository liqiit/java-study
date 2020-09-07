package com.liqi.rmi;

import com.liqi.service.HelloService;
import com.liqi.service.impl.HelloServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Title: ServiceRegistry
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/3
 */
public class ServiceRegistry {
    public static void main(String[] args) {
        try {
            HelloService helloService=new HelloServiceImpl();
            HelloService stub=(HelloService)UnicastRemoteObject.exportObject(helloService,0);
            Registry registry=LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.bind("hello-service",stub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
