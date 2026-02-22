# Cache Distribuído

Implementação de um sistema de cache em memória distribuído do zero em Java, parecido com Redis/Memcached.

Projeto educacional para aprender sistemas distribuídos, concorrência e estruturas de dados na prática.

## O que já funciona

**Core do cache:**
- Cache em memória com HashMap
- LRU (Least Recently Used) - remove itens menos usados quando enche
- Operações: PUT, GET, REMOVE, SIZE, CLEAR

**Persistência:**
- Salva snapshots em disco
- Carrega dados automaticamente ao reiniciar
- Comando SAVE para salvar manualmente

**Protocolo:**
- Parser de comandos (formato: `COMANDO chave valor`)
- Processador que executa comandos e valida erros

## O que falta fazer

**Networking:**
- Servidor TCP aceitando conexões
- Cliente Java
- Múltiplas conexões simultâneas

**Concorrência:**
- Thread-safety com ConcurrentHashMap
- Locks granulares
- Testes de stress

**Distribuição:**
- Consistent hashing
- Cluster com múltiplos nós
- Replicação de dados
- Descoberta de nós (gossip protocol)

**Tolerância a falhas:**
- Health checks
- Failover automático
- Redistribuição quando um nó cai

**Extras:**
- TTL (expiração de chaves)
- Mais tipos de dados (List, Set, Hash)
- Compressão
- AOF (Append Only File)
- Benchmark vs Redis

## Estrutura do código

```
src/main/java/com/cache/
├── core/
│   ├── Cache.java              # Cache principal
│   └── Main.java               # Testes
├── persistence/
│   └── Snapshot.java           # Salvar/carregar
└── protocol/
    ├── Command.java            # Estrutura de comando
    └── CommandProcessor.java   # Executa comandos
```

## Como rodar

```bash
git clone https://github.com/seu-usuario/cache-distribuido.git
cd cache-distribuido
mvn clean compile
mvn exec:java -Dexec.mainClass="com.cache.core.Main"
```

## Tecnologias

- Java 23
- Maven
- LinkedHashMap (LRU nativo)
- Serialização Java

## Conceitos estudados

- HashMap interno (hash function, buckets, colisões)
- LRU eviction
- Serialização e persistência
- Command pattern
- Thread-safety (próximo)
- Sistemas distribuídos (próximo)