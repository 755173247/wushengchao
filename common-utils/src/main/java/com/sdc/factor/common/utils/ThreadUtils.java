package com.sdc.factor.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程帮助类
 *
 * @author sean
 * @since 2019-03-24
 */
public class ThreadUtils {

    /**
     * 线程工厂
     */
    static class SDCThreadFactory implements ThreadFactory {

        private static final Logger LOGGER = LoggerFactory.getLogger(SDCThreadFactory.class);

        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

        private static final ThreadGroup THREAD_GROUP = new ThreadGroup("sdcfactor");

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        private final boolean daemon;

        private SDCThreadFactory(String namePrefix, boolean daemon) {
            this.namePrefix = namePrefix;
            this.daemon = daemon;
        }

        public static ThreadGroup getThreadGroup() {
            return THREAD_GROUP;
        }

        public static ThreadFactory create(String namePrefix, boolean daemon) {
            return new SDCThreadFactory(namePrefix, daemon);
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(THREAD_GROUP, r, THREAD_GROUP.getName() + "-" + this.namePrefix + "-" + this.threadNumber.getAndIncrement());
            t.setDaemon(this.daemon);
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            LOGGER.debug("Thread " + t.getName() + " has been created.");
            return t;
        }
    }

    /**
     * 创建固定容量的线程池
     * @param nThreads 最大线程数量
     * @param namePrefix 线程名称前缀
     * @param daemon 是否为守护线程
     */
    public static ExecutorService fixedExecutorService(int nThreads, String namePrefix, boolean daemon) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), SDCThreadFactory.create(namePrefix, daemon));
    }

    /**
     * 创建固定容量且为守护线程的线程池
     * @param nThreads 最大线程数量
     * @param namePrefix 线程名称前缀
     */
    public static ExecutorService fixedExecutorService(int nThreads, String namePrefix) {
        return fixedExecutorService(nThreads, namePrefix, true);
    }

    /**
     * 创建固定容量的定时执行的线程池
     * @param nThreads 最大线程数量
     * @param namePrefix 线程名称前缀
     * @param daemon 是否为守护线程
     */
    public static ScheduledExecutorService scheduledExecutorService(int nThreads, String namePrefix, boolean daemon) {
        return new ScheduledThreadPoolExecutor(nThreads, SDCThreadFactory.create(namePrefix, daemon));
    }

    /**
     * 创建固定容量且为守护线程的定时人执行的线程池
     * @param nThreads 最大线程数量
     * @param namePrefix 线程名称前缀
     */
    public static ScheduledExecutorService scheduledExecutorService(int nThreads, String namePrefix) {
        return scheduledExecutorService(nThreads, namePrefix, true);
    }
}
