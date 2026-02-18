package com.cache.core;

import com.cache.persistance.Snapshot;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private final Map<String, String> storage;
    private final Snapshot snapshot;

    public Cache(int capacidade, String caminhoSnapshot) {
        this.snapshot = new Snapshot(caminhoSnapshot);

        Map<String, String> dadosCarregados;
        try {
            dadosCarregados = snapshot.carregar();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERRO ao carregar snapshot: " + e.getMessage());
            dadosCarregados = new LinkedHashMap<>();
        }

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

        storage.putAll(dadosCarregados);
        System.out.println("Cache inicializado com " + storage.size() + " itens\n");
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

    public void salvarSnapshot() {
        try {
            snapshot.salvar(new LinkedHashMap<>(storage));
        } catch (IOException e) {
            System.err.println("ERRO ao salvar snapshot: " + e.getMessage());
        }
    }
}
