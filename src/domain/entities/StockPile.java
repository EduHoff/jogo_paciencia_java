package domain.entities;

public class StockPile extends CardStack {

    /*
        É o monte inicial que sobra. Ele fica virado para baixo.

        Regra de Entrada: Nenhuma carta entra aqui durante o jogo normal (apenas na preparação ou se o descarte resetar).

        Comportamento: O jogador apenas retira cartas daqui.
    */

    @Override
    public boolean canPush(Card card) {
        return false;
    }
}
