package domain.services;

import domain.entities.*;
import java.util.ArrayList;
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
        // TODO: Implementar lógica de geração de cartas, embaralhar e distribuir
    }

    public boolean moveFromWasteToTableau(int tableauIndex) {
        // TODO: Validar se a waste não está vazia e se tableau.canPush() aceita a carta
        return false;
    }

    public boolean moveFromWasteToFoundation(int foundationIndex) {
        // TODO: Validar se a waste não está vazia e se foundation.canPush() aceita a carta
        return false;
    }

    public boolean moveBetweenTableaus(int sourceIndex, int destinationIndex) {
        // TODO: Lógica de movimentação entre colunas (respeitando as regras de cores/valores)
        return false;
    }

    public void drawCard() {
        // TODO: Implementar lógica de compra/reciclagem de cartas
    }

    public boolean checkWinCondition() {
        // TODO: Implementar verificação de vitória
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