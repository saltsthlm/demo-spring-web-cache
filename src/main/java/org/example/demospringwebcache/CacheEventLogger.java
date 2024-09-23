package org.example.demospringwebcache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        System.out.println("CacheEvent=" + cacheEvent.getType().name()
                + " k=" + cacheEvent.getKey()
                + "; old=" + cacheEvent.getOldValue()
                + "; new=" + cacheEvent.getNewValue());
    }
}
