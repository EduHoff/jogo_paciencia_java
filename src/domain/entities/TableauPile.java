package domain.entities;

import java.util.Stack;

import domain.enums.Rank;

public class TableauPile extends CardStack {

    @Override
    public boolean canPush(Card card) {
        
        if (isEmpty()) return card.getRank() == Rank.KING;

        Card topCard = peek();
        
        if (topCard.isHidden()) return false;

        boolean differentColor = card.getSuit().isRed() != topCard.getSuit().isRed();
        boolean previousRank = card.getRank().getValue() == topCard.getRank().getValue() - 1;

        return differentColor && previousRank;
    }

    public Stack<Card> getCardsInternal() {
        return this.cards;
    }
}
