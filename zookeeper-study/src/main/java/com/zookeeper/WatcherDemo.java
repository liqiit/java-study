package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class WatcherDemo {
    public static void main(String[] args) {
        //PathChildCache 针对子节点的创建/更新/删除
        //NodeCache 针对当前节点的变化事件
        //TreeCache 综合事件
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 4))
                .build();
        curatorFramework.start();
        try {
            //addListenerWithNode(curatorFramework);
            addListenerWithChildrenNode(curatorFramework);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addListenerWithNode(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/watch", false);
        NodeCacheListener nodeCacheListener = () -> {
            System.out.println("receive Node changed");
            System.out.println(nodeCache.getCurrentData().getPath() + "/" + new String(nodeCache.getCurrentData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();


    }

    private static void addListenerWithChildrenNode(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework, "/watch", true);
        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println(pathChildrenCacheEvent.getType() + "->" + pathChildrenCacheEvent.getData().getPath() + "|"
                    + new String(pathChildrenCacheEvent.getData().getData()));
        };
        nodeCache.getListenable().addListener(pathChildrenCacheListener);
        nodeCache.start(PathChildrenCache.StartMode.NORMAL);


    }

    private static void addListenerWithAll(CuratorFramework curatorFramework) {
        TreeCache treeCache = new TreeCache(curatorFramework, "/watch");
        TreeCacheListener treeCacheListener = (client, event) -> {
            System.out.println(event.getData().getPath());
        };
        treeCache.getListenable().addListener(treeCacheListener);
    }
}
