/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoxadrez;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Isabela
 */
public class Tabuleiro extends JFrame {

    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JLabel label;
    private JButton botao;
    private JButton botao2;
    private int nJogadas;
    private int derrotas=1;

    private Peca[][] peca;
    private int colunaTabu, linhaTabu; // coordenadas da peça selecionada
    private boolean jogadorDaVez; // indica qual é o jogador da vez

    public Tabuleiro() {
        //Construindo o JFrame
        super("Xadrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        painel3 = new JPanel();
        painel1 = new JPanel();
        painel2 = new JPanel();
        botao = new JButton("Novo jogo");
        botao2 = new JButton("Desistir da partida");

        add(painel1);
        //label = new JLabel("1");
        add(painel2, BorderLayout.NORTH);
        painel2.add(botao);
        painel2.add(botao2);

        painel1.setBackground(new Color(50, 168, 82));
//        add(painel3, BorderLayout.EAST);
        //painel1.add(label);

        this.peca = new Peca[8][8];
        // Distribuindo as peças pelo tabuleiro
        for (int lin = 0; lin < 8; lin++) {
            // determinando a cor da peca, false = preta, true = branca
            boolean cor = false;
            //Se linha maior q 4 retorna true, e as peças brancas começam a ser aparecer no tabuleiro.
            if (lin > 4) {
                cor = true;
            }
            for (int col = 0; col < 8; col++) {
                //Colocando peças no tabuleiro.

                if (lin == 1 || lin == 6) {
                    //Método para colocar peças brancas ou pretas, passando linha, coluna e a cor da peça.
                    this.peca[lin][col] = new Peao(cor, col, lin);
                }
                if ((lin == 0 && col == 0) || (lin == 0 && col == 7) || (lin == 7 && col == 0) || (lin == 7 && col == 7)) {
                    this.peca[lin][col] = new Torre(cor, col, lin);
                }
                if ((lin == 0 && col == 1) || (lin == 0 && col == 6) || (lin == 7 && col == 1) || (lin == 7 && col == 6)) {
                    this.peca[lin][col] = new Cavalo(cor, col, lin);
                }
                if ((lin == 0 && col == 2) || (lin == 0 && col == 5) || (lin == 7 && col == 2) || (lin == 7 && col == 5)) {
                    this.peca[lin][col] = new Bispo(cor, col, lin);
                }
                if ((lin == 0 && col == 3) || (lin == 7 && col == 3)) {
                    this.peca[lin][col] = new Dama(cor, col, lin);
                }
                if ((lin == 0 && col == 4) || (lin == 7 && col == 4)) {
                    this.peca[lin][col] = new Rei(cor, col, lin);
                }
            }
        }
        this.jogadorDaVez = true;
        colunaTabu = -1;
        linhaTabu = -1;
        //Método para repetir o método paint
        //this.repaint();

        //Ação do mouse
        painel1.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Coordenadas X x Y
                int x = e.getX();
                int y = e.getY();

                int coluna, linha;

                coluna = (x - 40) / 70;
                linha = (y - 40) / 70;
                mover(coluna, linha);//Passando para o método mover a linha e a coluna.
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }

        });

        //Ação dos botões
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Tabuleiro t = new Tabuleiro();
                t.setSize(640, 700);
                t.setVisible(true);
                t.setLocationRelativeTo(null);
            }
        });
        
        botao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                derrotas++;
                dispose();
            }
        });

    }
    
    public int getDerrotas(){
        return derrotas;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //método para desenhar o tabuleiro
        this.desenhar(g);
        this.colocarPecas(g);
        //Fechando graphics q será reaberto no repaint.
        g.dispose();
    }

    public void desenhar(Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.white);
        g.fillRect(38, 100, 2, 560);

        g.setColor(Color.white);
        g.fillRect(38, 100, 560, 2);

        g.setColor(Color.white);
        g.fillRect(38, 660, 560, 2);

        g.setColor(Color.white);
        g.fillRect(598, 100, 2, 560);

        for (int i = 40; i <= 560; i += 140) {
            for (int j = 102; j <= 560; j += 140) {
                g.setColor(Color.white);
                g.fillRect(i, j, 70, 70);
            }
        }

        for (int i = 110; i <= 630; i += 140) {
            for (int j = 172; j <= 630; j += 140) {
                g.setColor(Color.white);
                g.fillRect(i, j, 70, 70);
            }
        }
    }

    public void colocarPecas(Graphics g) {
        //Colocando as peças
        for (int lin = 0; lin < 8; lin++) {
            for (int col = 0; col < 8; col++) {
                if (peca[lin][col] != null) {
                    this.peca[lin][col].desenhar(g, painel1);
                }
            }
        }
    }

    public void mover(int col, int lin) {
        Graphics g = this.getGraphics();
        //Lógica reaproveitada, de um jogo de damas.
        if (peca[lin][col] != null && peca[lin][col].isCor() == this.jogadorDaVez) { // se clicou em uma casa com peça ou vazia

            // Se clicou em uma casa que contém a peça do jogador da vez,
            // então selecione a peça e guarde suas coordenadas x e y
            // se houver peça do jogador selecionada, apague seleção anterior
            if (colunaTabu != -1) {
                Color c = Color.gray;
                if (((colunaTabu % 2 == 0) && linhaTabu % 2 == 0)
                        || ((colunaTabu % 2 != 0) && linhaTabu % 2 != 0)) {
                    c = Color.white;
                }

                g.setColor(c);
                g.drawRoundRect(colunaTabu * 70 + 50, linhaTabu * 70 + 110, 50, 50, 5, 5);
            }
            // fazendo a seleção
            g.setColor(Color.orange);
            g.drawRoundRect(col * 70 + 50, lin * 70 + 110, 50, 50, 5, 5);
            colunaTabu = col;
            linhaTabu = lin;

        } else { // Se clicou em uma casa vazia
            // se houver peça do jogador selecionada
            // entao, indica que é uma movimentação de peça
            if (colunaTabu != -1) {
                int l = linhaTabu;
                int c = colunaTabu;
                boolean cor = peca[linhaTabu][colunaTabu].isCor();
                // Faça a peça executar sua lógica de movimentação
                if (peca[linhaTabu][colunaTabu].mover(lin, col, peca)) {

                    this.colunaTabu = -1;

                    nJogadas++;

                    //Laço que verifica se o rei entrou em xeque
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (peca[i][j] instanceof Rei) {
                                if (peca[i][j].verificarRei(peca)) {
                                    if (peca[i][j].isCor() == this.peca[lin][col].isCor()) {
                                        if (this.peca[lin][col] instanceof Peao) {
                                            this.peca[lin][col] = null;
                                            this.peca[l][c] = new Peao(cor, c, l);
                                            this.jogadorDaVez = !this.jogadorDaVez;

                                        } else {
                                            peca[lin][col].mover(l, c, peca);
                                            this.jogadorDaVez = !this.jogadorDaVez;

                                        }
                                    }

                                }

                            }
                        }
                    }
                    // dá a vez para o outro jogador
                    this.jogadorDaVez = !this.jogadorDaVez;
                    this.repaint();

                }
            }
        }

    }

}
