package com.datastruct.custom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName CacheUtil
 * @Deacription 缓存工具类，
 * @Author liqi
 * @Date 2021/4/14 10:53
 * @Version 1.0
 **/
public class CacheUtil {
    /**
     * 当前缓存个数
     */
    private static Integer CURRENT_SIZE = 0;
    /**
     * 时间一分钟
     */
    static final Long ONE_MINUTE = 60 * 1000L;
    /**
     * 缓存对象
     */
    private static final Map<String, CacheObj> CACHE_OBJECT_MAP = new ConcurrentHashMap<>();
    /**
     * 清理过期缓存是否在运行
     */
    private static volatile Boolean CLEAN_THREAD_IS_RUN = false;
    /***
     * 未使用最长毫秒数，即最后一次使用与当前时间时间差最大值
     */
    private static final Long maxTimeMillis = 1000L;
    static {
        startCleanThread();
    }
    /**
     * 设置缓存
     */
    public static void setCache(String cacheKey, Object cacheValue) {
        Long lastUpdateTime = System.currentTimeMillis();
        CURRENT_SIZE = CURRENT_SIZE + 1;
        CacheObj cacheObj = new CacheObj(cacheValue, lastUpdateTime);
        CACHE_OBJECT_MAP.put(cacheKey, cacheObj);
    }

    /**
     * 获取缓存
     */
    public static Object getCache(String cacheKey) {
        if (checkCache(cacheKey)) {
            return CACHE_OBJECT_MAP.get(cacheKey).getCacheValue();
        }
        return null;
    }

    /**
     * 判断缓存在不在
     */
    private static boolean checkCache(String cacheKey) {
        CacheObj cacheObj = CACHE_OBJECT_MAP.get(cacheKey);
        if (cacheObj == null) {
            return false;
        }
        return true;
    }

    /**
     * 删除过期的缓存
     */
    static void deleteTimeOut() {
        Long currentTime = System.currentTimeMillis();
        for (Map.Entry<String, CacheObj> entry : CACHE_OBJECT_MAP.entrySet()) {
            Long ttlTime = currentTime - entry.getValue().getLastUpdateTime();
            if (ttlTime > maxTimeMillis) {
                deleteCache(entry.getKey());
            }
        }
    }

    /**
     * 删除某个缓存
     */
    public static void deleteCache(String cacheKey) {
        Object cacheValue = CACHE_OBJECT_MAP.remove(cacheKey);
        if (cacheValue != null) {
            CURRENT_SIZE = CURRENT_SIZE - 1;
        }
    }

    /**
     * 设置清理线程的运行状态为正在运行
     */
    static void setCleanThreadRun() {
        CLEAN_THREAD_IS_RUN = true;
    }

    /**
     * 开启清理过期缓存的线程
     */
    private static void startCleanThread() {
        if (!CLEAN_THREAD_IS_RUN) {
            CleanTimeOutThread cleanTimeOutThread = new CleanTimeOutThread();
            Thread thread = new Thread(cleanTimeOutThread);
            //设置为后台守护线程
            thread.setDaemon(true);
            thread.start();
        }
    }


}

class CacheObj {
    /**
     * 缓存对象
     */
    private Object CacheValue;
    /**
     * 缓存最后更新时间
     */
    private Long lastUpdateTime;

    CacheObj(Object cacheValue, Long lastUpdateTime) {
        CacheValue = cacheValue;
        this.lastUpdateTime = lastUpdateTime;
    }

    Object getCacheValue() {
        return CacheValue;
    }

    Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public String toString() {
        return "CacheObj {" +
                "CacheValue = " + CacheValue +
                ", lastUpdateTime = " + lastUpdateTime +
                '}';
    }
}

/**
 * 每一分钟清理一次过期缓存
 */
class CleanTimeOutThread implements Runnable {

    @Override
    public void run() {
        System.out.println("开始定时删除过期缓存值");
        CacheUtil.setCleanThreadRun();
        while (true) {
            CacheUtil.deleteTimeOut();
            try {
                Thread.sleep(CacheUtil.ONE_MINUTE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}