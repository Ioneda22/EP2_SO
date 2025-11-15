# EP2 - Leitores e Escritores

## Estrutura do Projeto

O código está dividido da seguinte forma:

* **`noSolution/`**: Contém a implementação com bloqueio único (exclusão mútua).
    * `Main.java`: O "play" do programa. Executa a simulação desta versão.
    * `NoSolution.java`: Organiza o teste, cria as *threads* e gere o `ReentrantLock` global.
    * `entities/`: Contém as *threads*.
        * `Worker.java`: Classe base que faz a *thread* adquirir o `Lock` antes de trabalhar e soltá-lo no final.
        * `Reader.java`: *Thread* que apenas lê dados.
        * `Writer.java`: *Thread* que escreve dados.

* **`Solution/`**: Contém a implementação com a lógica de Leitores/Escritores.
    * `Main.java`: O "play" desta versão.
    * `Solution.java`: Organiza o teste, cria as *threads* e gere os Semáforos (`_wrt`, `_mutex`) e o contador de leitores.
    * `entities/`:
        * `Worker.java`: Classe base que define os métodos `enterRegion()` e `exitRegion()`.
        * `Reader.java`: Implementa a lógica de entrada/saída dos leitores (gere o contador e os semáforos).
        * `Writer.java`: Implementa a lógica de entrada/saída dos escritores (tenta bloquear o semáforo de escrita).

* **`utils/`**:
    * `ArchiveReader.java`: Ferramenta simples para ler o ficheiro `bd.txt`.
    * `bd.txt`: O "recurso partilhado", um ficheiro de texto usado como base de dados.

* **`output/`**:
    * `results_noSolution.csv`: Resultados da versão com bloqueio único.
    * `results_Solution.csv`: Resultados da versão com Leitores/Escritores.

## Como Executar

É preciso ter o JDK (Java Development Kit) instalado. Todos os comandos devem ser executados **a partir do diretório raiz do projeto** (a pasta que contém `noSolution/`, `Solution/`, etc.).

### 1. Compilação

Para compilar todos os ficheiros `.java` de uma vez:

```bash
# No Linux/macOS
javac $(find . -name "*.java")

# No Windows (PowerShell)
javac (Get-ChildItem -Recurse -Filter *.java).FullName
````

Ou, de forma mais simples, compilando apenas os ficheiros `Main`, que o `javac` encontra o resto:

```bash
javac noSolution/Main.java Solution/Main.java
```

### 2\. Execução

Depois de compilar, pode executar as duas versões:

**Para executar a simulação com bloqueio exclusivo (noSolution):**

```bash
java noSolution.Main
```

Isto irá executar o teste e criar (ou substituir) o ficheiro `output/results_noSolution.csv`.

**Para executar a simulação com prioridade dos leitores (Solution):**

```bash
java Solution.Main
```

Isto irá executar o teste e criar (ou substituir) o ficheiro `output/results_Solution.csv`.

## Fontes

Este projeto contou com o auxílio de ferramentas de Inteligência Artificial para estruturação, depuração do código e também para a escrita desse próprio README.

As implementações dos mecanismos de concorrência foram baseadas nos conceitos e exemplos descritos nos seguintes recursos:

* **Java Concurrent Locks (Baeldung):**
  `https://www.baeldung.com/java-concurrent-locks`

* **Readers-Writers Problem - Readers' Preference (GeeksforGeeks):**
  `https://www.geeksforgeeks.org/operating-systems/readers-writers-problem-set-1-introduction-and-readers-preference-solution/`

<!-- end list -->
