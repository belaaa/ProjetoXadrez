package projetoxadrez;

/**
 *
 * @author Isabela
 */
public class Bispo extends Peca {

    public Bispo(boolean c, int col, int lin) {
        super(c, col, lin);
        this.definirCor("bispo");
    }

    @Override
    public boolean mover(int linDestino, int colDestino, Peca[][] peca) {
        boolean moveu = false;

        if (linDestino + colDestino == this.getLinha() + this.getColuna() || linDestino - colDestino == this.getLinha() - this.getColuna()) {

            if (this.verificarCasasBispo(linDestino, colDestino, peca)) {//Verifica se as casas estão disponíveis
                this.moverPeca(linDestino, colDestino, peca);
                moveu = true;
            }

        }
        return moveu;
    }

}
