package domain.entities;

public class TableauPile extends CardStack {

    /*
        Onde a mágica e a organização do jogo acontecem. Aqui as cartas são empilhadas em ordem decrescente e alternando as cores.

        Regra de Entrada:

            Se a coluna estiver totalmente vazia, a maioria das regras clássicas diz que ela apenas aceita um Rei (Rank.KING).

            Se já houver cartas, a nova carta deve ter uma cor diferente (um Suit vermelho sobre um preto, ou vice-versa) e o valor numérico deve ser exatamente -1 em relação à carta do topo (ex: uma Dama QUEEN vermelha sobre um Rei KING preto).
    */

    @Override
    public boolean canPush(Card card) {
        return false;
    }
}
