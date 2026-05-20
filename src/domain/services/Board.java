package domain.services;

import domain.entities.*;
import domain.enums.Rank;
import domain.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Board {
    private final StockPile stock;
    private final WastePile waste;
    private final List<FoundationPile> foundations;
    private final List<TableauPile> tableaus;

    public Board() {
        this.stock = new StockPile();
        this.waste = new WastePile();
        this.foundations = new ArrayList<>();
        this.tableaus = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            foundations.add(new FoundationPile());
        }
        for (int i = 0; i < 7; i++) {
            tableaus.add(new TableauPile());
        }
    }

    public void setupGame() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }

        Collections.shuffle(deck);

        int deckIndex = 0;
        for (int i = 0; i < 7; i++) {
            TableauPile currentTableau = tableaus.get(i);
            for (int j = 0; j <= i; j++) {
                Card card = deck.get(deckIndex++);
                if (j == i) {
                    card.setHidden(false);
                }
                currentTableau.forcePush(card); 
            }
        }

        while (deckIndex < deck.size()) {
            stock.forcePush(deck.get(deckIndex++));
        }
    }

    public void drawCard() {
        if (stock.isEmpty() && waste.isEmpty()) {
            throw new IllegalArgumentException("Não há mais cartas no estoque ou no descarte!");
        }

        if (stock.isEmpty()) {
            while (!waste.isEmpty()) {
                Card card = waste.pop();
                card.setHidden(true);
                stock.forcePush(card);
            }
            return;
        }

        Card card = stock.pop();
        card.setHidden(false);
        waste.forcePush(card);
    }

    public void moveFromWasteToTableau(int tableauIndex) {
        if (waste.isEmpty()) throw new IllegalArgumentException("O descarte está vazio!");
        TableauPile target = tableaus.get(tableauIndex);
        
        target.push(waste.peek());
        waste.pop();
    }

    public void moveFromWasteToFoundation(int foundationIndex) {
        if (waste.isEmpty()) throw new IllegalArgumentException("O descarte está vazio!");
        FoundationPile target = foundations.get(foundationIndex);
        
        target.push(waste.peek());
        waste.pop();
    }

    public void moveFromTableauToFoundation(int tableauIndex, int foundationIndex) {
        TableauPile source = tableaus.get(tableauIndex);
        if (source.isEmpty()) throw new IllegalArgumentException("Coluna de origem vazia!");
        
        FoundationPile target = foundations.get(foundationIndex);
        target.push(source.peek());
        source.pop();
        
        revealTopCard(source);
    }

    public void moveBetweenTableaus(int sourceIndex, int destinationIndex) {
        TableauPile source = tableaus.get(sourceIndex);
        TableauPile target = tableaus.get(destinationIndex);
        
        if (source.isEmpty()) throw new IllegalArgumentException("Coluna de origem vazia!");

        Object[] sourceCards = getStackAsArray(source);
        int firstOpenIndex = -1;
        for (int i = 0; i < sourceCards.length; i++) {
            if (!((Card) sourceCards[i]).isHidden()) {
                firstOpenIndex = i;
                break;
            }
        }

        if (firstOpenIndex == -1) throw new IllegalArgumentException("Nenhuma carta aberta para mover!");

        Card baseCard = (Card) sourceCards[firstOpenIndex];
        target.push(baseCard);
        target.pop();

        Stack<Card> tempStorage = new Stack<>();
        int itemsToMove = sourceCards.length - firstOpenIndex;
        for (int i = 0; i < itemsToMove; i++) {
            tempStorage.push(source.pop());
        }
        while (!tempStorage.isEmpty()) {
            target.forcePush(tempStorage.pop());
        }

        revealTopCard(source);
    }

    private void revealTopCard(TableauPile tableau) {
        if (!tableau.isEmpty() && tableau.peek().isHidden()) {
            tableau.peek().setHidden(false);
        }
    }

    public boolean checkWinCondition() { 
        int totalCardsInFoundations = 0;
        for (FoundationPile fp : foundations) {
            totalCardsInFoundations += fp.size();
        }
        return totalCardsInFoundations == 52; 
    }

    private Object[] getStackAsArray(CardStack stack) {
        try {
            java.lang.reflect.Field field = CardStack.class.getDeclaredField("cards");
            field.setAccessible(true);
            Stack<?> internalStack = (Stack<?>) field.get(stack);
            return internalStack.toArray();
        } catch (Exception e) {
            return new Object[0];
        }
    }

    public StockPile getStock() { 
        return stock;
    }

    public WastePile getWaste() { 
        return waste; 
    }
    
    public List<FoundationPile> getFoundations() { 
        return foundations; 
    }

    public List<TableauPile> getTableaus() { 
        return tableaus; 
    }
}