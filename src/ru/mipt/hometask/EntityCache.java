package ru.mipt.hometask;

import java.util.HashMap;

public class EntityCache<T> {
    private static HashMap<Object, Object> cache;

    public Object getFromCache(Object entityIdentifier){
        return cache.get(entityIdentifier);
    }

    public void putInCache(Object entityIdentifier, Object entity) {
        cache.put(entityIdentifier, entity);
    }
}
