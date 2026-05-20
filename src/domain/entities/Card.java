package domain.entities;

import domain.enums.Rank;
import domain.enums.Suit;

public class Card {

    private Rank rank;
    private Suit suit;
    private boolean isHidden;

    public Card() {}

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.isHidden = true;
    }

    @Override
    public String toString() {
        if(isHidden) return "[X]";

        return "[" + this.rank.getSymbol() + this.suit.getSymbol() + "]";
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
}
