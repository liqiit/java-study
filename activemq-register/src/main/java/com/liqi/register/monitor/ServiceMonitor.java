package com.liqi.register.monitor;

import com.liqi.register.configuration.ZookeeperConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Title: ServiceMonitor
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/8/28
 */
@Order(1)
@Component
@Slf4j
public class ServiceMonitor implements CommandLineRunner {
    private final String parentPath = "/activemq";
    private Set<String> SERVICE_URL_LIST = new CopyOnWriteArraySet<>();
    @Autowired
    private ZookeeperConfiguration zookeeperConfiguration;

    @Override
    public void run(String... args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zookeeperConfiguration.baseSleepTimeMs, zookeeperConfiguration.maxRetries);
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString(zookeeperConfiguration.zookeeperServer)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(zookeeperConfiguration.sessionTimeoutMs)
                .connectionTimeoutMs(zookeeperConfiguration.connectionTimeoutMs)
                .build();

        curatorFramework.start();
        //加载所有服务地址
        //SERVICE_URL_LIST = curatorFramework.getChildren().forPath(parentPath);
        //建立监听动态维护服务地址列表
        TreeCache treeCache = new TreeCache(curatorFramework, parentPath);
        TreeCacheListener treeCacheListener = (client, event) -> {
            if(event.getData()!=null){
                String path=event.getData().getPath();
                String[]splitArray=path.split("/");
                if(splitArray.length==4){
                    if (TreeCacheEvent.Type.NODE_ADDED == event.getType()) {
                        log.info(event.getType() + "->" + event.getData().getPath());
                        SERVICE_URL_LIST.add(splitArray[2]);
                    }
                    if (TreeCacheEvent.Type.NODE_REMOVED == event.getType()) {
                        log.info(event.getType() + "->" + event.getData().getPath());
                        SERVICE_URL_LIST.remove(splitArray[2]);
                    }
                }

            }

        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
//        PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework, parentPath, true);
//        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {
//            if (PathChildrenCacheEvent.Type.CHILD_ADDED == pathChildrenCacheEvent.getType()) {
//                log.info(pathChildrenCacheEvent.getType() + "->" + pathChildrenCacheEvent.getData().getPath());
//                SERVICE_URL_LIST.add(pathChildrenCacheEvent.getData().getPath());
//            }
//            if (PathChildrenCacheEvent.Type.CHILD_REMOVED == pathChildrenCacheEvent.getType()) {
//                log.info(pathChildrenCacheEvent.getType() + "->" + pathChildrenCacheEvent.getData().getPath());
//                SERVICE_URL_LIST.remove(pathChildrenCacheEvent.getData().getPath());
//            }
//        };
//        nodeCache.getListenable().addListener(pathChildrenCacheListener);
//        nodeCache.start(PathChildrenCache.StartMode.NORMAL);
    }

    public String getServiceURL() {
        Random random = new Random();
        int n = random.nextInt(SERVICE_URL_LIST.size());
        List<String>list=new ArrayList<>(SERVICE_URL_LIST);
        return list.get(n);
    }
}
