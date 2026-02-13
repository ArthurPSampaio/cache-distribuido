package com.cache.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private final Map<String, String> storage;

    public Cache(int capacidade) {
        this.storage = new LinkedHashMap<String, String>(capacidade, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                boolean deveRemover = size() > capacidade;
                if (deveRemover) {
                    System.out.println("EVICT (LRU): " + eldest.getKey() + " (cache cheio)");
                }
                return deveRemover;
            }
        };
    }

    public void put(String chave, String valor) {
        storage.put(chave, valor);
        System.out.println("PUT: " + chave + " = " + valor + " (total: " + storage.size() + ")");
    }

    public String get(String chave) {
        String valor = storage.get(chave);
        System.out.println("GET: " + chave + " -> " + valor);
        return valor;
    }

    public void remove(String chave) {
        storage.remove(chave);
        System.out.println("REMOVE: " + chave);
    }

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
        System.out.println("CLEAR: cache limpo");
    }
}
