package domain.entities;

public class StockPile extends CardStack {

    @Override
    public boolean canPush(Card card) {
        return true;
    }
}
