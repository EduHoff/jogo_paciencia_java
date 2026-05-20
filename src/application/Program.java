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
            String input = ui.getPlayerInput().trim().toLowerCase();
            
            if (input.equals("quit") || input.equals("exit")) {
                ui.showMessage("Jogo finalizado pelo jogador.");
                break;
            }
            
            if (input.equals("draw")) {
                try {
                    board.drawCard();
                } catch (IllegalArgumentException e) {
                    ui.showMessage("Erro: " + e.getMessage());
                }
                continue;
            }

            if (input.startsWith("move")) {
                String[] parts = input.split("\\s+");
                if (parts.length != 3) {
                    ui.showMessage("Comando inválido! Use: move [origem] [destino] (Ex: move c1 c2)");
                    continue;
                }

                String src = parts[1];
                String dst = parts[2];

                try {

                    if (src.equals("w")) {
                        if (dst.startsWith("c")) {
                            int tIdx = Integer.parseInt(dst.substring(1)) - 1;
                            board.moveFromWasteToTableau(tIdx);
                        } else if (dst.startsWith("f")) {
                            int fIdx = Integer.parseInt(dst.substring(1)) - 1;
                            board.moveFromWasteToFoundation(fIdx);
                        } else {
                            ui.showMessage("Destino inválido! Use c1-c7 ou f1-f4.");
                        }
                    } 

                    else if (src.startsWith("c")) {
                        int srcIdx = Integer.parseInt(src.substring(1)) - 1;
                        
                        if (dst.startsWith("c")) {
                            int dstIdx = Integer.parseInt(dst.substring(1)) - 1;
                            board.moveBetweenTableaus(srcIdx, dstIdx);
                        } else if (dst.startsWith("f")) {
                            int fIdx = Integer.parseInt(dst.substring(1)) - 1;
                            board.moveFromTableauToFoundation(srcIdx, fIdx);
                        } else {
                            ui.showMessage("Destino inválido! Use c1-c7 ou f1-f4.");
                        }
                    } else {
                        ui.showMessage("Origem inválida! Use 'w' ou colunas de 'c1' a 'c7'.");
                    }
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    ui.showMessage("Erro de sintaxe! Verifique se os números das colunas (1-7) ou fundações (1-4) estão corretos.");
                } catch (IllegalArgumentException e) {
                    ui.showMessage("Movimento inválido: " + e.getMessage());
                }
            } else {
                ui.showMessage("Comando não reconhecido. Digite 'draw', 'move [origem] [destino]' ou 'quit'.");
            }
            
            try { Thread.sleep(1200); } catch (InterruptedException ignored) {}
        }

        if (board.checkWinCondition()) {
            ui.drawBoard(board);
            ui.showMessage("PARABÉNS! Você organizou todas as cartas e venceu o Paciência clássico!");
        }
    }
}