package domain.services;

import domain.entities.*;
import domain.enums.Rank;
import domain.enums.Suit;

import java.util.Random;

public class Board {
    private final Queue<Card> monte; 
    
    private final Stack<Card>[] fundacoes; 
    
    private final LinkedList<Card>[] colunas; 

    @SuppressWarnings("unchecked")
    public Board() {
        this.monte = new Queue<>();
        this.fundacoes = new Stack[4];
        this.colunas = new LinkedList[7];
        
        for (int i = 0; i < 4; i++) {
            fundacoes[i] = new Stack<>();
        }
        for (int i = 0; i < 7; i++) {
            colunas[i] = new LinkedList<>();
        }
    }

    public void setupGame() {
        Card[] deck = new Card[52];
        int idx = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck[idx++] = new Card(rank, suit);
            }
        }

        Random rand = new Random();
        for (int i = deck.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

        int deckIndex = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                Card card = deck[deckIndex++];
                if (j == i) {
                    card.setHidden(false);
                }
                colunas[i].addLast(card);
            }
        }

        while (deckIndex < deck.length) {
            monte.enqueue(deck[deckIndex++]);
        }

        if (!monte.isEmpty()) {
            monte.peek().setHidden(false);
        }
    }

    public void ciclarFila() {
        if (monte.length() == 0) throw new IllegalArgumentException("O monte está vazio!");
    
        Card card = monte.peek();
        card.setHidden(true);
        monte.dequeue();
        monte.enqueue(card);
        
        monte.peek().setHidden(false);
    }

    public void moveFilaParaPilha(int fIdx) {
        if (monte.length() == 0) throw new IllegalArgumentException("Monte vazio!");
        Card card = monte.peek();
        Stack<Card> fundacao = fundacoes[fIdx];

        if (fundacao.isEmpty()) {
            if (card.getRank() != Rank.ACE) throw new IllegalArgumentException("A pilha final deve começar com um Ás!");
        } else {
            Card top = fundacao.peek();
            if (card.getSuit() != top.getSuit() || card.getRank().getValue() != top.getRank().getValue() + 1) {
                throw new IllegalArgumentException("Carta inválida para esta pilha final!");
            }
        }

        fundacao.push(card);
        monte.dequeue();
    }

    public void moveFilaParaLista(int cIdx) {
        if (monte.length() == 0) throw new IllegalArgumentException("Monte vazio!");
        Card card = monte.peek();
        LinkedList<Card> coluna = colunas[cIdx];

        validarInsercaoColuna(card, coluna);

        card.setHidden(false);
        coluna.addLast(card);
        monte.dequeue();
    }

    public void moveListaParaPilha(int cIdx, int fIdx) {
        LinkedList<Card> coluna = colunas[cIdx];
        if (coluna.length() == 0) throw new IllegalArgumentException("Coluna vazia!");
        
        Card card = coluna.get(coluna.length() - 1); 
        Stack<Card> fundacao = fundacoes[fIdx];

        if (fundacao.isEmpty()) {
            if (card.getRank() != Rank.ACE) throw new IllegalArgumentException("Deve começar com Ás!");
        } else {
            Card top = fundacao.peek();
            if (card.getSuit() != top.getSuit() || card.getRank().getValue() != top.getRank().getValue() + 1) {
                throw new IllegalArgumentException("Movimento inválido para a pilha final!");
            }
        }

        fundacao.push(card);
        coluna.removeLast();
        revelarNovaUltimaCarta(coluna);
    }

    public void moveEntreListas(int srcIdx, int dstIdx, Card cartaAlvo) {
        LinkedList<Card> src = colunas[srcIdx];
        LinkedList<Card> dst = colunas[dstIdx];

        if (src.length() == 0) throw new IllegalArgumentException("Coluna de origem vazia!");

        int targetIdx = -1;
        for (int i = 0; i < src.length(); i++) {
            Card c = src.get(i);
            if (!c.isHidden() && c.equals(cartaAlvo)) {
                targetIdx = i;
                break;
            }
        }

        if (targetIdx == -1) throw new IllegalArgumentException("Carta alvo não encontrada ou está oculta!");

        validarInsercaoColuna(src.get(targetIdx), dst);

        int countToMove = src.length() - targetIdx;
        Card[] bloco = new Card[countToMove];
        
        for (int i = 0; i < countToMove; i++) {
            bloco[i] = src.get(targetIdx);
            src.removeMiddle(targetIdx);
        }

        for (Card c : bloco) {
            dst.addLast(c);
        }

        revelarNovaUltimaCarta(src);
    }

    private void validarInsercaoColuna(Card card, LinkedList<Card> coluna) {
        if (coluna.length() == 0) {
            if (card.getRank() != Rank.KING) throw new IllegalArgumentException("Espaços vazios só aceitam Reis!");
        } else {
            Card top = coluna.get(coluna.length() - 1);
            if (top.isHidden()) throw new IllegalArgumentException("A carta de destino está virada para baixo!");
            
            boolean corDiferente = card.getSuit().isRed() != top.getSuit().isRed();
            boolean valorDecrescente = card.getRank().getValue() == top.getRank().getValue() - 1;

            if (!corDiferente || !valorDecrescente) {
                throw new IllegalArgumentException("As colunas exigem cores alternadas e ordem decrescente!");
            }
        }
    }

    private void revelarNovaUltimaCarta(LinkedList<Card> coluna) {
        if (coluna.length() > 0) {
            Card ultima = coluna.get(coluna.length() - 1);
            if (ultima.isHidden()) {
                ultima.setHidden(false);
            }
        }
    }

    public boolean checkWinCondition() {
        int total = 0;
        for (Stack<Card> f : fundacoes) total += f.length();
        return total == 52;
    }

    public Queue<Card> getMonte() { 
        return monte; 
    }

    public Stack<Card>[] getFundacoes() { 
        return fundacoes; 
    }

    public LinkedList<Card>[] getColunas() { 
        return colunas; 
    }
}