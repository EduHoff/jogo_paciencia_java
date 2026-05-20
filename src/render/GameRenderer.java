package render;

import domain.services.Board;
import domain.entities.Card;
import domain.entities.CardStack;
import domain.entities.TableauPile;
import utils.ConsoleUtils;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GameRenderer implements GameUI {

    private final Scanner scanner = new Scanner(System.in);

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
        System.out.println("===============================================================");
    }

    private void renderTopRow(Board board) {
        String stockStr = board.getStock().isEmpty() ? "[ ]" : "[X]";
        System.out.print("Stock: " + stockStr + "   ");

        String wasteStr = board.getWaste().isEmpty() ? "[ ]" : board.getWaste().peek().toString();
        System.out.print("Waste: " + wasteStr + "         ");

        System.out.print("Foundations: ");
        for (int i = 0; i < 4; i++) {
            var foundation = board.getFoundations().get(i);
            String foundationStr = foundation.isEmpty() ? "[ ]" : foundation.peek().toString();
            System.out.print(foundationStr + " ");
        }
        System.out.println();
    }

    private void renderTableaus(Board board) {
        List<TableauPile> tableaus = board.getTableaus();

        int maxRows = 0;
        for (TableauPile tableau : tableaus) {
            if (tableau.size() > maxRows) {
                maxRows = tableau.size();
            }
        }

        if (maxRows == 0) {
            System.out.println("  [ ]    [ ]    [ ]    [ ]    [ ]    [ ]    [ ]");
            return;
        }

        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            System.out.print("  ");
            
            for (int colIndex = 0; colIndex < 7; colIndex++) {
                TableauPile currentTableau = tableaus.get(colIndex);
                

                Object[] cardsArray = currentTableau.peek() != null ? getStackAsArray(currentTableau) : null;

                if (cardsArray != null && rowIndex < cardsArray.length) {
                    Card card = (Card) cardsArray[rowIndex];
                    
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

    private Object[] getStackAsArray(CardStack stack) {
        try {
            java.lang.reflect.Field field = CardStack.class.getDeclaredField("cards");
            field.setAccessible(true);
            Stack<?> internalStack = (Stack<?>) field.get(stack);
            return internalStack.toArray();
        } catch (Exception e) {
            return new Object[0];
        }
    }

    @Override
    public String getPlayerInput() {
        System.out.print("\nDigite o comando (ex: 'draw', 'quit', 'move'): ");
        return scanner.nextLine();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(">> " + message);
    }
}