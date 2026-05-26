package application;

import domain.services.Board;
import render.GameUI;
import utils.ConsoleUtils;
import render.GameRenderer;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        GameUI ui = new GameRenderer();
        boolean jogoIniciado = false;

        while (true) {
            if (!jogoIniciado) {
                ConsoleUtils.clear();
                System.out.println("\n=== PACIÊNCIA - MENU PRINCIPAL ===");
                System.out.println("1. Embaralhar e Iniciar Jogo");
                System.out.println("2. Sair");
                System.out.print("Escolha uma opção: ");
                String op = scanner.nextLine().trim();

                if (op.equals("1")) {
                    board = new Board();
                    board.setupGame();
                    jogoIniciado = true;
                    ui.showMessage("Tabuleiro montado e cartas embaralhadas com sucesso!");
                } else if (op.equals("2") || op.equalsIgnoreCase("quit")) {
                    System.out.println("Aplicação encerrada.");
                    break;
                } else {
                    ConsoleUtils.clear();
                    System.out.println("Opção inválida!");
                }
            } else {
                ui.drawBoard(board);
                
                System.out.println("\n--- MENU DE AÇÕES DO JOGO ---");
                System.out.println("a. Movimentar carta da fila para pilha final");
                System.out.println("b. Movimentar da fila para a própria fila (Ciclar Monte)");
                System.out.println("c. Movimentar carta da fila para uma coluna (Lista)");
                System.out.println("d. Movimentar carta de uma coluna (Lista) para pilha final");
                System.out.println("e. Movimentar de uma coluna (Lista) para outra coluna");
                System.out.println("f. Reiniciar o jogo (Voltar ao menu)");
                System.out.println("g. Ver estado atual do jogo");
                System.out.println("h. Sair do programa");
                System.out.print("Escolha a ação: ");
                
                String action = scanner.nextLine().trim().toLowerCase();

                try {
                    switch (action) {
                        case "a":
                            System.out.print("Informe a pilha final de destino (1 a 4): ");
                            int fIdxA = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            board.moveFilaParaPilha(fIdxA);
                            ui.showMessage("Comando executado: Fila -> Pilha Final " + (fIdxA + 1));
                            break;
                            
                        case "b":
                            board.ciclarFila();
                            ui.showMessage("Comando executado: Carta enviada para o fim da fila circular.");
                            break;
                            
                        case "c":
                            System.out.print("Informe a coluna de destino (1 a 7): ");
                            int cIdxC = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            board.moveFilaParaLista(cIdxC);
                            ui.showMessage("Comando executado: Fila -> Coluna " + (cIdxC + 1));
                            break;
                            
                        case "d":
                            System.out.print("Informe a coluna de origem (1 a 7): ");
                            int cIdxD = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            System.out.print("Informe a pilha final de destino (1 a 4): ");
                            int fIdxD = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            board.moveListaParaPilha(cIdxD, fIdxD);
                            ui.showMessage("Comando executado: Coluna " + (cIdxD + 1) + " -> Pilha Final " + (fIdxD + 1));
                            break;
                            
                        case "e":
                            System.out.print("Informe a coluna de origem (1 a 7): ");
                            int srcIdx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            System.out.print("Informe a coluna de destino (1 a 7): ");
                            int dstIdx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            
                            System.out.print("Digite o símbolo da carta que quer mover (Ex: 8S para 8 de Espadas, AD para Ás de Ouros): ");
                            String cardToken = scanner.nextLine().trim().toUpperCase();
                            
                            domain.entities.Card cartaAlvo = null;
                            var listaOrigem = board.getColunas()[srcIdx];
                            for (int i = 0; i < listaOrigem.length(); i++) {
                                var c = listaOrigem.get(i);
                                String tokenText = c.getRank().getSymbol() + c.getSuit().getSymbol();
                                if (tokenText.replace("♦","D").replace("♥","H").replace("♠","S").replace("♣","C").equalsIgnoreCase(cardToken) 
                                    || tokenText.equalsIgnoreCase(cardToken)) {
                                    cartaAlvo = c;
                                    break;
                                }
                            }
                            
                            if (cartaAlvo == null) throw new IllegalArgumentException("Carta não encontrada ou digitação incorreta!");
                            board.moveEntreListas(srcIdx, dstIdx, cartaAlvo);
                            ui.showMessage("Bloco movido com sucesso entre as listas ligadas!");
                            break;
                            
                        case "f":
                            jogoIniciado = false;
                            ui.showMessage("Jogo reiniciado.");
                            ConsoleUtils.clear();
                            break;
                        
                        case "g":
                            ui.drawBoard(board);
                            ui.showMessage("Estado atual do tabuleiro atualizado na tela.");
                            break;
                            
                        case "h":
                            System.out.println("Aplicação encerrada.");
                            System.exit(0);
                            break;
                            
                        default:
                            ui.showMessage("Opção inválida! Escolha de 'a' até 'h'.");
                    }
                } catch (Exception e) {
                    ui.showMessage("Erro na operação: " + e.getMessage());
                }

                if (board.checkWinCondition()) {
                    ui.drawBoard(board);
                    System.out.println("\nPARABÉNS! VOCÊ VENCEU O JOGO!");
                    jogoIniciado = false;
                }
                
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        }

        scanner.close();
    }
}