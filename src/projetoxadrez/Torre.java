package projetoxadrez;

/**
 *
 * @author Isabela
 */
public class Torre extends Peca {

    public Torre(boolean c, int col, int lin) {
        super(c, col, lin);
        this.definirCor("torre");
    }

    @Override
    public boolean mover(int linDestino, int colDestino, Peca[][] peca) {
        boolean moveu = false;

        if (linDestino == this.getLinha() || colDestino == this.getColuna()) {
            if (this.verificarCasasTorre(linDestino, colDestino, peca)) {
                this.moverPeca(linDestino, colDestino, peca);
                moveu = true;

            }
        }
        return moveu;
    }

}
