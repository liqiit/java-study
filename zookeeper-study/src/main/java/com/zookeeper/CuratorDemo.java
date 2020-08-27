package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

public class CuratorDemo {
    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 4))
                .build();
        curatorFramework.start();
        try {
            //增加节点
            createNode(curatorFramework);
            //查询节点
            //queryNode(curatorFramework);
            //修改节点
            //updateNode(curatorFramework);
            //删除节点
            //deleteNode(curatorFramework);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createNode(CuratorFramework curatorFramework) throws Exception {
        List<ACL> list = new ArrayList<>();
        ACL acl = new ACL();
        acl.setPerms(ZooDefs.Perms.READ);
        acl.setId(new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);
        curatorFramework.create()
                .withMode(CreateMode.PERSISTENT)
                .withACL(list)
                .forPath("/data/auth", "test".getBytes());
    }

    private static void updateNode(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().forPath("/data/program", "up2".getBytes());
    }

    private static void queryNode(CuratorFramework curatorFramework) throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/data/program");
        String value = new String(bytes);
        System.out.println("value:" + value);
    }

    private static void deleteNode(CuratorFramework curatorFramework) throws Exception {
        Stat stat = new Stat();
        curatorFramework.getData().storingStatIn(stat).forPath("/data/program");
        curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");
    }
}
