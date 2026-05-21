package domain.entities;


abstract public class CardStack {

    protected final Stack<Card> cards;

    public CardStack() {
        this.cards = new Stack<>();
    }

    public Card pop() {
        
        Card aux = cards.peek();
        cards.pop();
        return aux;
    }

    public Card peek() {
        if (cards.isEmpty()) return null;
        return cards.peek();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.length();
    }

    public void push(Card card) {
        if (canPush(card)) {
            cards.push(card);
            return;
        }

        throw new IllegalArgumentException("Jogada inválida para esta pilha!");
    }

    public void forcePush(Card card) {
        this.cards.push(card);
    }

    public abstract boolean canPush(Card card);

    public LinkedList<Card> getCardsForRendering() {
        LinkedList<Card> list = new LinkedList<>();
        Stack<Card> aux = new Stack<>();

        while (!this.isEmpty()) {
            aux.push(this.pop());
        }

        while (!aux.isEmpty()) {
            Card card = aux.peek();
            
            aux.pop(); 
            this.forcePush(card);
            
            list.addLast(card); 
        }

        return list;
    }
}
