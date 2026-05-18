package application;

import domain.services.Board;
import render.GameUI;
import render.GameRenderer;

public class Program {

    public static void main(String[] args) {

        Board board = new Board();
        
        GameUI ui = new GameRenderer();

        board.setupGame();

        while (!board.checkWinCondition()) {
            
            ui.drawBoard(board);
            
            String command = ui.getPlayerInput().trim().toLowerCase();
            
            if (command.equals("quit") || command.equals("exit")) {
                ui.showMessage("Jogo encerrado pelo jogador.");
                break;
            }
            
            try {
                if (command.equals("draw")) {
                    board.drawCard();
                } else if (command.startsWith("move")) {
                    // Exemplo esperado de comando: "move c1 c2" ou "move waste f1"
                    // TODO: Fazer o split da string para pegar as origens e destinos e chamar os métodos do board
                    ui.showMessage("Processando comando de movimentação...");
                } else {
                    ui.showMessage("Comando desconhecido! Tente 'draw', 'move [src] [dst]' ou 'quit'.");
                }
            } catch (IllegalArgumentException e) {
                // Captura os erros de jogadas inválidas que seu CardStack vai lançar
                ui.showMessage("Movimento inválido: " + e.getMessage());
            }
        }

        if (board.checkWinCondition()) {
            ui.showMessage("Parabéns! Você venceu o jogo!");
        }
    }
}