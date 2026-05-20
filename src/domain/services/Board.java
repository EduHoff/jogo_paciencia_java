package domain.services;

import domain.entities.*;
import domain.enums.Rank;
import domain.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                
                currentTableau.push(card); 
            }
        }

        while (deckIndex < deck.size()) {
            stock.push(deck.get(deckIndex++));
        }
    }


    public void drawCard() {}

    public boolean checkWinCondition() { 
        return false; 
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