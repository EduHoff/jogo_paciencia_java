package domain.enums;

public enum Suit {
    CLUBS(false, "♣"),    // Paus
    DIAMONDS(true, "♦"),  // Ouro
    HEARTS(true, "♥"),    // Copas
    SPADES(false, "♠");   // Espadas

    private final boolean isRed;
    private final String symbol;

    Suit(boolean isRed, String symbol) {
        this.isRed = isRed;
        this.symbol = symbol;
    }

    public boolean isRed() {
        return isRed;
    }

    public String getSymbol() {
        return symbol;
    }
}