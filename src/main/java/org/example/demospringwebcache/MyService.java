package org.example.demospringwebcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Service
public class MyService {

    private final CacheManager cacheManager;

    @Autowired
    public MyService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Cacheable("test1")
    public long computePublic(int number) {
        System.out.println("MyService.computePublic " + number);
        return System.currentTimeMillis();
    }

    @CacheEvict(value = "test1", allEntries = true)
//    @Scheduled(fixedRate = 10000)
    public void clearCache() {
        System.out.println("MyService.clearCache");
    }

    public void checkCache() {
        Cache cache = cacheManager.getCache("test1"); // org.springframework.cache.Cache
        if (cache instanceof JCacheCache jcache) {
            System.out.println("it's jcache...");
            javax.cache.Cache<Object, Object> nativeCache = jcache.getNativeCache();
            for (javax.cache.Cache.Entry<Object, Object> next : nativeCache) {
                System.out.println(next.getKey() + " : " + next.getValue());
            }
        } else if (cache != null) {
            System.out.println("it's the generic cache, printing the map:");
            ConcurrentMapCache cmc = (ConcurrentMapCache) cache;
            ConcurrentMap<Object, Object> cm = cmc.getNativeCache();
            for (Map.Entry<Object, Object> e : cm.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        } else {
            System.out.println("cache is null");
        }
    }
}
