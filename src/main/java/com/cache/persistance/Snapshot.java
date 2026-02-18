package com.cache.persistance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Snapshot {
    private final String caminhoArquivo;

    public Snapshot(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void salvar(Map<String, String> dados) throws IOException {
        Path caminho = Paths.get(caminhoArquivo);
        Files.createDirectories(caminho.getParent());

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(dados);
            System.out.println("SNAPSHOT: salvo em " + caminhoArquivo + " (" + dados.size() + " itens)");
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            System.out.println("SNAPSHOT: arquivo não encontrado, iniciando cache vazio");
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(caminhoArquivo))) {
            Map<String, String> dados = (Map<String,String>) ois.readObject();
            System.out.println("SNAPSHOT: carregado de " + caminhoArquivo + " (" + dados.size() + " itens)");
            return dados;
        }
    }
}
