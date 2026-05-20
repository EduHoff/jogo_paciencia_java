package domain.entities;

public class WastePile extends CardStack {
        
    @Override
    public boolean canPush(Card card) {
        return !card.isHidden();
    }
}
