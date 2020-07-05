package projetoxadrez;

import javax.swing.JOptionPane;

/**
 *
 * @author Isabela
 */
public class Peao extends Peca {

    int primeiroMov = 0;

    public Peao(boolean c, int col, int lin) {
        super(c, col, lin);
        this.definirCor("peao");
    }

    @Override
    public boolean mover(int linDestino, int colDestino, Peca[][] peca) {
        
        boolean moveu = false;
       
        if (peca[linDestino][colDestino] == null) {

            if (this.isCor()) {//Peao branco
                if (primeiroMov == 0) {//Quando movimenta 2 casas
                    if (linDestino == this.getLinha() - 1 && colDestino == this.getColuna() || linDestino == this.getLinha() - 2 && colDestino == this.getColuna() && peca[this.getLinha() - 1][this.getColuna()] == null) {
                        this.moverPeca(linDestino, colDestino, peca);
                        primeiroMov++;
                        moveu = true;
                    }
                } else {//Movimenta 1 só
                    if (linDestino == this.getLinha() - 1 && colDestino == this.getColuna()) {
                        this.moverPeca(linDestino, colDestino, peca);
                        moveu = true;
                        primeiroMov++;
                    }
                }

            } else {//Peao preto
                if (primeiroMov == 0) {//Quando movimenta 2 casas
                    if (linDestino == this.getLinha() + 1 && colDestino == this.getColuna() || linDestino == this.getLinha() + 2 && colDestino == this.getColuna() && peca[this.getLinha() + 1][this.getColuna()] == null) {
                        this.moverPeca(linDestino, colDestino, peca);
                        primeiroMov++;
                        moveu = true;
                    }
                } else {//Movimenta 1 só
                    if (linDestino == this.getLinha() + 1 && colDestino == this.getColuna()) {
                        this.moverPeca(linDestino, colDestino, peca);
                        moveu = true;
                        primeiroMov++;
                    }
                }
            }
        } else {// Movimento de captura
            if (this.isCor()) {//Peao branco
                if (linDestino == this.getLinha() - 1 && (colDestino == this.getColuna() - 1 || colDestino == this.getColuna() + 1)) {
                    this.moverPeca(linDestino, colDestino, peca);
                    moveu = true;
                    primeiroMov++;
                }

            } else {//Peao preto
                if (linDestino == this.getLinha() + 1 && (colDestino == this.getColuna() + 1 || colDestino == this.getColuna() - 1)) {
                    this.moverPeca(linDestino, colDestino, peca);
                    moveu = true;
                    primeiroMov++;
                }

            }
        }
        //promoverPeao(linDestino, colDestino, peca); 
        return moveu;
    }

    public void promoverPeao(int linDestino, int colDestino, Peca[][] peca) {
        if (linDestino == 7 || linDestino == 0) {
            String[] opcoes = {"Dama", "Torre", "Biso", "Cavalo"};
            int opcao = JOptionPane.showOptionDialog(null, "Escolha a peça para a promoção:", "Promover peão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, null);
            
            switch(opcao){
                case 0:
                    peca[linDestino][colDestino] = new Dama(this.isCor(), linDestino, colDestino);
                    System.out.println(linDestino+","+colDestino);
                    break;
                case 1:
                    peca[linDestino][colDestino] = new Torre(this.isCor(), linDestino, colDestino);
                    break;
                case 2:
                    peca[linDestino][colDestino] = new Bispo(this.isCor(), linDestino, colDestino);
                    break;
                case 3:
                    peca[linDestino][colDestino] = new Cavalo(this.isCor(), linDestino, colDestino);
                    break;
            }
        }
    }

}
