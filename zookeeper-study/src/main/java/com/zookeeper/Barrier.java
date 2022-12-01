package com.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Barrier
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/3/4 10:54
 * @Version 1.0
 **/
public class Barrier implements Watcher, Runnable {
    private String root;
    private ZooKeeper zooKeeper;

    public Barrier(String address, String root) throws IOException {
        try {
            this.root=root;
            this.zooKeeper = new ZooKeeper(address,3000,this);
        } catch (IOException e) {
            e.printStackTrace();
            this.zooKeeper = null;
        }
    }
    @Override
    public void run() {
        try {
            Stat stat = zooKeeper.exists(root, true);
            //Barrier节点存在，线程等待
            if (stat!=null){
                System.out.println(Thread.currentThread().getName()+"——barrier节点存在，线程等待");
                synchronized (this){
                    this.wait();
                }
            }
            businessCode();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void businessCode() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"——执行业务逻辑耗时：2s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        try {
            for (int i=0;i<10;i++) {
                executorService.execute(new Barrier("127.0.0.1:2181","/barrier"));
            }
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() != Event.EventType.NodeDeleted) return;
        try {
            //查看Barrier节点是否存在
            Stat stat = zooKeeper.exists(root, true);
            //Barrier节点消失，唤起所有等待线程
            if (stat == null) {
                System.out.println(Thread.currentThread().getName() + "——barrier节点消失，唤起所有线程");
                synchronized (this) {
                    this.notifyAll();
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
