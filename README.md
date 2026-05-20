# jogo_paciencia_java

## Primeira execução / Rebuild
```
docker compose up --build
```

## Iniciar
```
docker compose run --rm jogo_paciencia_java
```

## Encerrar
```
docker compose down
```

# Como Jogar (Comandos do Terminal)

O jogo funciona através de comandos de texto baseados em **Origem** e **Destino**. Use as seguintes siglas para se referir às pilhas:

- `w`: Descarte (*Waste*)
- `c1` a `c7`: Colunas do Tabuleiro (*Tableau Columns*)
- `f1` a `f4`: Pilhas de Fundação (*Foundations*)

---

## 1. Comprar Cartas

Quando não houver jogadas disponíveis no tabuleiro, compre uma nova carta do estoque.

```bash
draw
```

> **Nota:** Se o estoque acabar, digitar `draw` novamente irá reciclar automaticamente as cartas do descarte de volta para o estoque.

---

## 2. Mover do Descarte (`w`)

Mova a carta que está aberta no topo da pilha de descarte para o jogo.

### Para uma coluna do tabuleiro (Ex: Coluna 1)

```bash
move w c1
```

### Para uma fundação (Ex: Fundação 3)

```bash
move w f3
```

---

## 3. Mover de uma Coluna (`c1` a `c7`)

Mova cartas que já estão no tabuleiro para organizar o jogo ou pontuar.

### Mover entre colunas (Ex: Da coluna 2 para a coluna 5)

```bash
move c2 c5
```

> O sistema move automaticamente blocos inteiros de cartas ordenadas a partir da carta selecionada.

### Enviar para a Fundação para pontuar (Ex: Da coluna 6 para a fundação 1)

```bash
move c6 f1
```

> **Dica:** Sempre que você mover a última carta aberta de uma coluna, a carta oculta logo abaixo dela será desvirada automaticamente.

---

## 4. Encerrar o Jogo

Para fechar o jogo a qualquer momento e retornar ao terminal:

```bash
quit
```

Também aceita o comando:

```bash
exit
```
