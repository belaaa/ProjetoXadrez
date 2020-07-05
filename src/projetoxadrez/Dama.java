package projetoxadrez;

/**
 *
 * @author Isabela
 */
public class Dama extends Peca {

    public Dama(boolean c, int col, int lin) {
        super(c, col, lin);
        this.definirCor("rainha");
    }

    @Override
    public boolean mover(int linDestino, int colDestino, Peca[][] peca) {
        boolean moveu = false;

        if (linDestino + colDestino == this.getLinha() + this.getColuna() || linDestino - colDestino == this.getLinha() - this.getColuna()) {
            if (this.verificarCasasBispo(linDestino, colDestino, peca)) {//Verificação movimento do bispo
                this.moverPeca(linDestino, colDestino, peca);
                moveu = true;

            }

        } else if ((linDestino == this.getLinha() || colDestino == this.getColuna())) {
            if (this.verificarCasasTorre(linDestino, colDestino, peca)) {//Verificação movimento da torre
                this.moverPeca(linDestino, colDestino, peca);
                moveu = true;
            }
        }

        return moveu;
    }

}
