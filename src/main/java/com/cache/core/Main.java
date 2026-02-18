package com.cache.core;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Teste de Persistência ===\n");

        String arquivo = "data/cache-snapshot.dat";

        System.out.println("--- PRIMEIRA EXECUÇÃO ---");
        Cache cache1 = new Cache(10, arquivo);
        cache1.put("usuario:1", "João Silva");
        cache1.put("usuario:2", "Maria Santos");
        cache1.put("produto:42", "Notebook Dell");
        cache1.salvarSnapshot();

        System.out.println("\n--- SEGUNDA EXECUÇÃO (simulando restart) ---");
        Cache cache2 = new Cache(10, arquivo);

        System.out.println("--- Verificando se os dados foram carregados ---");
        cache2.get("usuario:1");
        cache2.get("usuario:2");
        cache2.get("produto:42");

        System.out.println("\nTotal de itens: " + cache2.size());
    }
}