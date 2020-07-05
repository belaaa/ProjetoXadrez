package projetoxadrez;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Isabela
 */
public abstract class Peca {

    //private boolean xeque = false;
    private boolean cor; // true para preta, false para branca
    private int coluna;//Coluna retornada do tabuleiro
    private int linha;//linha retornada do tabuleiro
    private ImageIcon peca;

    //variáveis para verificar se a torre ja moveu
//    private boolean jaMoveuTorreB0;
//    private boolean jaMoveuTorreP0;
//    private boolean jaMoveuTorreB7;
//    private boolean jaMoveuTorreP7;
    public Peca(boolean c, int col, int lin) {
        this.coluna = col;
        this.linha = lin;
        this.cor = c;
    }

    public void definirCor(String nomePeca) {
        //String para definir a cor.
        String corDaPeca = this.cor ? nomePeca + ".br.png"/*true*/ : nomePeca + ".pr.png"/*false*/;//(linha de código reaproveitada)
        this.peca = new ImageIcon(this.getClass().getResource("/Imagens/" + corDaPeca));//Definindo umas das duas imagens acima.
    }

    //método de retorno da coluna
    public int getColuna() {
        return coluna;
    }

    //método para localizar a coluna que está sendo usada
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    //método de retorno da linha
    public int getLinha() {
        return linha;
    }
//método para localizar a linha que stá sendo usada

    public void setLinha(int linha) {
        this.linha = linha;
    }

    //Retorna a cor true ou false
    public boolean isCor() {
        return cor;
    }

//    public boolean isJaMoveuTorreB0() {
//        return jaMoveuTorreB0;
//    }
//
//    public boolean isJaMoveuTorreP0() {
//        return jaMoveuTorreP0;
//    }
//
//    public boolean isJaMoveuTorreB7() {
//        return jaMoveuTorreB7;
//    }
//
//    public boolean isJaMoveuTorreP7() {
//        return jaMoveuTorreP7;
//    }
    public void desenhar(Graphics g, JPanel ondeDesenhar) {
        //Código reaproveitado.
        // Função que desenha as peças na coord0enada definida.
        g.drawImage(this.peca.getImage(), 50 + (70 * this.coluna), 110 + (70 * this.linha), ondeDesenhar);
    }

    public abstract boolean mover(int linDestino, int colDestino, Peca[][] peca);//Movimentação específica de cada peça

    public void moverPeca(int linDestino, int colDestino, Peca[][] peca) {//Movimento padrão para trocar de casa
        peca[linDestino][colDestino] = null;
        peca[this.getLinha()][this.getColuna()] = null;
        this.setLinha(linDestino);
        this.setColuna(colDestino);
        peca[linDestino][colDestino] = this;
    }

    public boolean verificarCasasBispo(int linDestino, int colDestino, Peca[][] peca) {//Método que não deixa pular uma peça
        boolean verificou = false;
        int casaOcupada = 0;

        if (linDestino > this.getLinha() && colDestino > this.getColuna()) {//Direção canto inferior direito
            for (int i = this.getLinha() + 1; i < linDestino; i++) {
                for (int j = this.getColuna() + 1; j < colDestino; j++) {
                    if (i - j == linDestino - colDestino) {
                        if (peca[i][j] != null) {
                            casaOcupada++;
                        }
                    }

                }
            }

        } else if (linDestino > this.getLinha() && colDestino < this.getColuna()) {//Direção canto inferior esquerdo
            for (int i = this.getLinha() + 1; i < linDestino; i++) {
                for (int j = this.getColuna() - 1; j > colDestino; j--) {
                    if (i + j == linDestino + colDestino) {
                        if (peca[i][j] != null) {
                            casaOcupada++;
                        }
                    }

                }
            }
        } else if (linDestino < this.getLinha() && colDestino < this.getColuna()) {//Direção canto superior esquerdo
            for (int i = this.getLinha() - 1; i > linDestino; i--) {
                for (int j = this.getColuna() - 1; j > colDestino; j--) {
                    if (i - j == linDestino - colDestino) {
                        if (peca[i][j] != null) {
                            casaOcupada++;
                        }
                    }
                }
            }
        } else {//Direção canto superior direito
            for (int i = this.getLinha() - 1; i > linDestino; i--) {
                for (int j = this.getColuna() + 1; j < colDestino; j++) {
                    if (i + j == linDestino + colDestino) {
                        if (peca[i][j] != null) {
                            casaOcupada++;
                        }
                    }

                }
            }
        }

        if (casaOcupada == 0) {
            verificou = true;
        }

        return verificou;
    }

    public boolean verificarCasasTorre(int linDestino, int colDestino, Peca[][] peca) {
        boolean verificou = false;
        int casaOcupada = 0;
        if (linDestino == this.getLinha()) {//Verificando pela coluna
            if (colDestino > this.getColuna()) {

                for (int i = this.getColuna() + 1; i < colDestino; i++) {//Laço para não deixar a peça pular uma casa ocupada
                    if (peca[this.getLinha()][i] != null) {
                        casaOcupada++;

                    }
                }

            } else {
                for (int i = this.getColuna() - 1; i > colDestino; i--) {//Laço para não deixar a peça pular uma casa ocupada
                    if (peca[this.getLinha()][i] != null) {
                        casaOcupada++;
                    }
                }
            }

        } else if (colDestino == this.getColuna()) {//Verificando pela linha

            if (linDestino > this.getLinha()) {

                for (int i = this.getLinha() + 1; i < linDestino; i++) {//Laço para não deixar a peça pular uma casa ocupada

                    if (peca[i][this.getColuna()] != null) {
                        casaOcupada++;
                    }

                }
            } else {
                for (int i = this.getLinha() - 1; i > linDestino; i--) {//Laço para não deixar a peça pular uma casa ocupada
                    if (peca[i][this.getColuna()] != null) {
                        casaOcupada++;
                    }
                }
            }
        }

        if (casaOcupada == 0) {//Retorna true se as casas estiverem livres
            verificou = true;

        }

//        if(peca[this.getLinha()][this.getColuna()] instanceof Torre){
//            //Impede o rei de fazer o roque se moveu a torre
//        if (this.getColuna() == 0 && peca[this.getLinha()][this.getColuna()].isCor()) {
//            jaMoveuTorreB0 = true;
//        } else if (this.getColuna() == 0 && peca[this.getLinha()][this.getColuna()].isCor() == false) {
//            jaMoveuTorreP0 = true;
//        } else if (this.getColuna() == 7 && peca[this.getLinha()][this.getColuna()].isCor()) {
//            jaMoveuTorreB7 = true;
//        } else {
//            jaMoveuTorreP7 = true;
//        }
//        }
        return verificou;

    }

    public boolean verificarRei(Peca[][] peca) {//Método que verifica quando o rei entra em xeque
        boolean xeque = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i - j == this.getLinha() - this.getColuna() || i + j == this.getLinha() + this.getColuna()) {
                    if (peca[i][j] != null) {
                        if (peca[i][j] instanceof Dama || peca[i][j] instanceof Bispo) {
                            if (peca[i][j].isCor() != this.isCor()) {
                                if (verificarCasasBispo(i, j, peca)) {
                                    System.out.println("rei em xeque");
                                    xeque = true;

                                }
                            }

                        }
                    }
                }

                if (j == this.getColuna() || i == this.getLinha()) {
                    if (peca[i][j] != null) {
                        if (peca[i][j] instanceof Dama || peca[i][j] instanceof Torre) {
                            if (peca[i][j].isCor() != this.isCor()) {
                                if (verificarCasasTorre(i, j, peca)) {
                                    System.out.println("rei em xeque");
                                    xeque = true;

                                }
                            }

                        }
                    }
                }

                if (peca[i][j] instanceof Cavalo && peca[i][j].isCor() != this.isCor()) {
                    if ((i == this.getLinha() - 2 && (j == this.getColuna() - 1 || j == this.getColuna() + 1))
                            || (i == this.getLinha() - 1 && (j == this.getColuna() - 2 || j == this.getColuna() + 2))
                            || (i == this.getLinha() + 2 && (j == this.getColuna() - 1 || j == this.getColuna() + 1))
                            || (i == this.getLinha() + 1 && (j == this.getColuna() - 2 || j == this.getColuna() + 2))) {

                        System.out.println("rei em xeque");
                        xeque = true;

                    }

                }

                if (i == this.getLinha() - 1 && (j == this.getColuna() - 1 || j == this.getColuna() + 1)) {//Peao preto
                    if (peca[i][j] instanceof Peao && peca[i][j].isCor() != this.isCor()) {
                        System.out.println("rei em xeque");
                        xeque = true;

                    }
                }

                if (i == this.getLinha() + 1 && (j == this.getColuna() - 1 || j == this.getColuna() + 1)) {//Peao branco
                    if (peca[i][j] instanceof Peao && peca[i][j].isCor() != this.isCor()) {
                        System.out.println("rei em xeque");
                        xeque = true;

                    }
                }
            }
        }

        return xeque;
    }

//    public void setXeque(boolean xeque) {
//        this.xeque = xeque;
//    }
}
