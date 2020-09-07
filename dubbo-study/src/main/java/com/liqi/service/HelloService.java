package com.liqi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Title: HelloService
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/3
 */
public interface HelloService extends Remote {
    String sayHello(String name) throws RemoteException;
}
