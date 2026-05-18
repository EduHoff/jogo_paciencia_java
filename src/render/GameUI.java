package render;

import domain.services.Board;

public interface GameUI {

    void drawBoard(Board board);
    
    String getPlayerInput();
    
    void showMessage(String message);
}
