package render;

import domain.services.Board;

public interface GameUI {

    void drawBoard(Board board);
    
    void showMessage(String message);
}
