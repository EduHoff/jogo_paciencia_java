package domain.entities;

public class FoundationPile extends CardStack {

    /*
        Aqui é onde você empilha as cartas para vencer o jogo, separadas por naipe, do menor para o maior.

        Regra de Entrada:

            Se a pilha estiver vazia, ela apenas aceita um Ás (Rank.ACE).

            Se já tiver cartas, a nova carta deve ser do mesmo naipe (Suit) e ter o valor numérico exatamente +1 em relação à carta que está no topo (ex: um THREE de Copas sobre um TWO de Copas).
    */

    @Override
    public boolean canPush(Card card) {
        return false;
    }
}
