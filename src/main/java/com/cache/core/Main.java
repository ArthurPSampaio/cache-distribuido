package com.cache.core;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cache Distribuído - Teste LRU ===\n");

        Cache cache = new Cache(3);

        System.out.println("--- Enchendo o cache (3 itens) ---");
        cache.put("A", "Item A");
        cache.put("B", "Item B");
        cache.put("C", "Item C");

        System.out.println("\n--- Acessando 'A' ---");
        cache.get("A");

        System.out.println("\n--- Adicionando 'D' ---");
        cache.put("D", "Item D");

        System.out.println("\n--- Verificando se 'B' foi removido ---");
        cache.get("B");

        System.out.println("\n--- Estado final ---");
        cache.get("A");
        cache.get("C");
        cache.get("D");

        System.out.println("\nTotal de itens no cache: " + cache.size());
    }
}