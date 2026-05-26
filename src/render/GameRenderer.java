package render;

import domain.services.Board;
import domain.entities.Card;
import domain.entities.LinkedList;
import domain.entities.Queue;
import utils.ConsoleUtils;

public class GameRenderer implements GameUI {

    private final Queue<String> gameLog = new Queue<>();

    @Override
    public void drawBoard(Board board) {
        ConsoleUtils.clear();
        System.out.println("==================== SOLITAIRE (PACIÊNCIA) ====================");
        System.out.println();
        
        renderTopRow(board);
        
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("  C1     C2     C3     C4     C5     C6     C7   (Columns)");
        System.out.println();
        
        renderTableaus(board);

        System.out.println("------------------------- LOG DO JOGO -------------------------");
        gameLog.printLog();
        System.out.println("===============================================================");
    }

    private void renderTopRow(Board board) {
        String monteStr = board.getMonte().length() == 0 ? "[ ]" : board.getMonte().peek().toString();
        System.out.print("Monte (Fila): " + monteStr + "   ");

        System.out.print("Foundations: ");
        for (int i = 0; i < 4; i++) {
            var foundation = board.getFundacoes()[i];
            String foundationStr = foundation.isEmpty() ? "[f"+(i+1)+"]" : foundation.peek().toString();
            System.out.print(foundationStr + " ");
        }
        System.out.println();
    }

    private void renderTableaus(Board board) {
        LinkedList<Card>[] colunas = board.getColunas();

        int maxRows = 0;
        for (LinkedList<Card> col : colunas) {
            if (col.length() > maxRows) {
                maxRows = col.length();
            }
        }

        if (maxRows == 0) {
            System.out.println("  [ ]    [ ]    [ ]    [ ]    [ ]    [ ]    [ ]");
            return;
        }

        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            System.out.print("  ");
            for (int colIndex = 0; colIndex < 7; colIndex++) {
                LinkedList<Card> currentList = colunas[colIndex];

                if (currentList != null && rowIndex < currentList.length()) {
                    Card card = currentList.get(rowIndex);
                    String cardStr = card.toString();

                    if (cardStr.contains("10")) {
                        System.out.print(cardStr + "   ");
                    } else {
                        System.out.print(cardStr + "    ");
                    }
                } else {
                    System.out.print("       "); 
                }
            }
            System.out.println();
        }
    }

    @Override
    public void showMessage(String message) {
        gameLog.enqueue(message);
        if (gameLog.length() > 4) {
            gameLog.dequeue();
        }
    }
}