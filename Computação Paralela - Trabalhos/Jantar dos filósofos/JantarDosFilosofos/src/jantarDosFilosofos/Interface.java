package jantarDosFilosofos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface {

    private MesaDeJantar mesa;

    private int larguraBase = 130;
    private int alturaBase = 100;

    private JFrame frame;
    private JPanel panelFilosofos;
    private JPanel panelMenu;
    private JPanel panel;
    private JTextField nome;
    private JButton botao;
    private JButton pause;
    private static int gridSize = 2;
    private boolean atualiza = true;

    private ArrayList<JLabel> labels = new ArrayList();
    private ArrayList<JPanel> panels = new ArrayList();

    public Interface(MesaDeJantar mesa) {
        this.mesa = mesa;
        this.gridSize = mesa.getLugares().size();

        this.frame = new JFrame("Jantar dos Filosofos");
        this.frame.setSize(this.larguraBase*this.gridSize,this.alturaBase*this.gridSize);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);

        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.frame.add(this.panel);

        this.panelMenu = new JPanel();
        this.panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        JLabel label = new JLabel("Nome do novo filosofo: ");
        this.panelMenu.add(label);
        this.nome = new JTextField();
        this.nome.setPreferredSize(new Dimension(200,30));
        this.panelMenu.add(this.nome);
        this.botao = new JButton("Adicionar");
        this.botao.addActionListener(this::botaoOnClick);
        this.panelMenu.add(this.botao);

        this.pause = new JButton("Pause");
        this.pause.addActionListener(this::pauseOnClick);
        this.panelMenu.add(this.pause);

        this.panelFilosofos = new JPanel();
        this.panelFilosofos.setSize(this.larguraBase*this.gridSize,this.alturaBase*gridSize);
        this.panelFilosofos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.panelFilosofos.setLayout(new GridLayout(this.gridSize,this.gridSize));

        this.panel.add(this.panelFilosofos, BorderLayout.CENTER);
        this.panel.add(this.panelMenu, BorderLayout.NORTH);

        for (Lugares lugar : mesa.getLugares()) {
            this.add(lugar);
        }
    }

    public void botaoOnClick(ActionEvent event) {
        String nome = this.nome.getText();
        Filosofo filosofo = new Filosofo(nome);
        this.mesa.add(filosofo);
        this.add(filosofo.getLugar());
        this.nome.setText("");
    }

    public void pauseOnClick(ActionEvent event) {
        if (this.atualiza) {
            this.atualiza = false;
            this.pause.setText("Play");
            this.pause.setBackground(Color.GREEN);
        }else{
            this.atualiza = true;
            this.pause.setText("Pause");
            this.pause.setBackground(Color.BLUE);
        }
    }

    public void add(Lugares lugar) {
        JPanel panelAux = new JPanel();
        this.panels.add(panelAux);
        panelAux.setSize(this.larguraBase,this.alturaBase);
        panelAux.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel nomeFilosofo = new JLabel(lugar.getFilosofo().getNome());
        panelAux.add(nomeFilosofo);

        JLabel estadoFilosofo = new JLabel(Filosofo.imprimeEstadosFilosofos(lugar.getFilosofo().getEstado()));
        panelAux.add(estadoFilosofo);
        this.labels.add(estadoFilosofo);

        this.panelFilosofos.add(panelAux);
    }

    public void atualiza() {
        if (this.atualiza) {
            int i = 0;
            for (Lugares lugar : mesa.getLugares()) {
                JLabel label = this.labels.get(i);
                label.setText(Filosofo.imprimeEstadosFilosofos(lugar.getFilosofo().getEstado()));

                JPanel panel = this.panels.get(i);
                panel.setBackground(corPorEstadoFilosofo(lugar.getFilosofo().getEstado()));
                i++;
            }
        }
    }

    public Color corPorEstadoFilosofo(int estado) {
        switch (estado) {
            case Filosofo.COMENDO:
                return Color.GREEN;
            case Filosofo.PENSANDO:
                return Color.BLUE;
            case Filosofo.COM_FOME:
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }

}
