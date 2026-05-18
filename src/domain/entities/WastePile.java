package domain.entities;

public class WastePile extends CardStack {
    
    /*
        Recebe as cartas que vêm do Estoque. Elas entram aqui viradas para cima.

        Regra de Entrada: Aceita qualquer carta, contanto que ela venha do Estoque.
    */
    
    @Override
    public boolean canPush(Card card) {
        return false;
    }
}
