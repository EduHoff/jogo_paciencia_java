package domain.entities;

import java.util.Stack;

abstract public class CardStack {

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
