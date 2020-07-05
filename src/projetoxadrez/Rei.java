package projetoxadrez;

/**
 *
 * @author Isabela
 */
public class Rei extends Peca {

    private boolean jaMoveu = false;

    public Rei(boolean c, int col, int lin) {
        super(c, col, lin);
        this.definirCor("rei");
    }

    @Override
    public boolean mover(int linDestino, int colDestino, Peca[][] peca) {
        boolean moveu = false;
        int lin = this.getLinha();
        int col = this.getColuna();
        if ((linDestino == this.getLinha() + 1 || linDestino == this.getLinha() - 1 || linDestino == this.getLinha())
                && (colDestino == this.getColuna() + 1 || colDestino == this.getColuna() - 1 || colDestino == this.getColuna())) {

            this.moverPeca(linDestino, colDestino, peca);
            if (this.verificarRei(peca) == false){
                moveu = true;
            }else{
                this.moverPeca(lin, col, peca);
            }
            
        } else {//Movimento do roque
            if (jaMoveu) {

            } else {
                if (colDestino == this.getColuna() + 2 && linDestino == this.getLinha()) {//roque pequeno
                    if (peca[7][7] instanceof Torre && this.isCor()) {//torre branca
                        this.moverPeca(linDestino, colDestino, peca);
                        peca[7][7].moverPeca(linDestino, colDestino - 1, peca);
                        moveu = true;
                    } else if (peca[0][7] instanceof Torre && this.isCor() == false) {//torre preta
                        
                        this.moverPeca(linDestino, colDestino, peca);
                        peca[0][7].moverPeca(linDestino, colDestino - 1, peca);
                        moveu = true;
                    }
                } else if (colDestino == this.getColuna() - 2 && linDestino == this.getLinha()) {//roque grande
                    if (peca[7][0] instanceof Torre && this.isCor()) {//torre branca
                        this.moverPeca(linDestino, colDestino, peca);
                        peca[7][0].moverPeca(linDestino, colDestino + 1, peca);
                        moveu = true;
                    } else if (peca[0][0] instanceof Torre && this.isCor() == false) {//torre preta
                        this.moverPeca(linDestino, colDestino, peca);
                        peca[0][0].moverPeca(linDestino, colDestino + 1, peca);
                        moveu = true;
                    }
                }
            }

        }

        jaMoveu = true;
        return moveu;
    }

}
