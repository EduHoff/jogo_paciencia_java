package domain.entities;

import domain.enums.Rank;

public class FoundationPile extends CardStack {

    @Override
    public boolean canPush(Card card) {
        
        if (isEmpty()) return card.getRank() == Rank.ACE;
        
        Card topCard = peek();
        boolean sameSuit = card.getSuit() == topCard.getSuit();
        boolean nextRank = card.getRank().getValue() == topCard.getRank().getValue() + 1;
        
        return sameSuit && nextRank;
    }
}
