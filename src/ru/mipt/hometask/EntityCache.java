package ru.mipt.hometask;

import java.util.HashMap;

public class EntityCache<T> { // Added contains and clear methods, moved to generics logic
    private HashMap<Integer, T> cache = new HashMap<>(); //since by contract entities are unambigiously identified by id

    public T getFromCache(Integer entityIdentifier){
        return cache.get(entityIdentifier);
    }

    public void putInCache(Integer entityIdentifier, T entity) {
        cache.put(entityIdentifier, entity);
    }

    public boolean contains(Integer entityIdentifier) {
        return cache.containsKey(entityIdentifier);
    }

    public void clear() {
        cache.clear();
    }
}
