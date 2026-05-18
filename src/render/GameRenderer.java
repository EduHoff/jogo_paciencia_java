package render;

import domain.services.Board;
import utils.ConsoleUtils;
import java.util.Scanner;

public class GameRenderer implements GameUI {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void drawBoard(Board board) {
        ConsoleUtils.clear();
        
        renderTopRow(board);
        
        System.out.println("\n-----------------------------------------------------");
        
        renderTableaus(board);
    }

    private void renderTopRow(Board board) {
        // TODO: Imprimir o topo do Stock, o topo da Waste e percorrer a lista de Foundations

        // Exemplo visual: [X] [Q♥]       [A♠] [ ] [ ] [ ]
    }

    private void renderTableaus(Board board) {
        // TODO: Implementar o loop de renderização vertical por linhas
    }

    @Override
    public String getPlayerInput() {
        System.out.print("\nEnter your move (e.g., 'draw' or 'move C1 C2'): ");
        return scanner.nextLine();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(">> " + message);
    }
}