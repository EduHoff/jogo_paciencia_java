package domain.entities;

import java.util.Stack;

abstract public class CardStack {

    /*
        1. A Base: CardStack (Superclasse)

        A sua classe CardStack será a mãe de todas as outras pilhas. Ela vai encapsular um java.util.Stack<Card> interno e oferecer os métodos clássicos (push, pop, peek, isEmpty).

        O grande truque de POO aqui é criar um método chamado canPush(Card card). Na classe mãe, ele pode simplesmente retornar true (ou ser abstrato), mas as classes filhas vão sobrescrever (@Override) esse método com suas próprias regras do jogo.
    */

    protected final Stack<Card> cards;

    public CardStack() {
        this.cards = new Stack<>();
    }

    public Card pop() {
        return cards.pop();
    }

    public Card peek() {
        return cards.peek();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public void push(Card card) {
        if (canPush(card)) {
            cards.push(card);
        }

        throw new IllegalArgumentException("Jogada inválida para esta pilha!");
    }

    public abstract boolean canPush(Card card);
}
