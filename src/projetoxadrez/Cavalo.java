package projetoxadrez;

/**
 *
 * @author Isabela
 */
public class Cavalo extends Peca {

    public Cavalo(boolean c, int col, int lin) {
        super(c, col, lin);
        this.definirCor("cavalo");
    }

    @Override
    public boolean mover(int linDestino, int colDestino, Peca[][] peca) {
        boolean moveu = false;

        if ((linDestino == this.getLinha() - 2 && (colDestino == this.getColuna() - 1 || colDestino == this.getColuna() + 1))
                || (linDestino == this.getLinha() - 1 && (colDestino == this.getColuna() - 2 || colDestino == this.getColuna() + 2))
                || (linDestino == this.getLinha() + 2 && (colDestino == this.getColuna() - 1 || colDestino == this.getColuna() + 1))
                || (linDestino == this.getLinha() + 1 && (colDestino == this.getColuna() - 2 || colDestino == this.getColuna() + 2))) {
            
            this.moverPeca(linDestino, colDestino, peca);
            moveu = true;
        }

        return moveu;
    }

}
