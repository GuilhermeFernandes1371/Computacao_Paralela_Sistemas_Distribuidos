package cliente;

import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class InterfaceCliente {

    private Cliente cliente;

    private JFrame frame;
    private JTextField mensagem;

    public InterfaceCliente(Cliente cliente) {
        this.cliente = cliente;

        this.frame = new JFrame("Cliente: " + this.cliente.getNome());
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLayout(new GridLayout(1,1));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        this.frame.add(panelPrincipal);

        this.iniciaMenu(panelPrincipal);
        this.iniciaPainelDeMensagens(panelPrincipal);

        this.frame.pack();
    }

    private void iniciaMenu(JPanel panelPrincipal) {
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(Color.RED);
        JLabel lIp = new JLabel("IP: " + Server.getServerIp());
        JLabel lGate = new JLabel("Porta: " + Server.getServerPort());
        panelMenu.add(lIp);
        panelMenu.add(lGate);

        panelPrincipal.add(panelMenu, BorderLayout.NORTH);
    }

    private void iniciaPainelDeMensagens(JPanel panelPrincipal) {
        JPanel panelMensagem = new JPanel();
        panelMensagem.setLayout(new GridLayout(1,2));
        panelPrincipal.add(panelMensagem, BorderLayout.CENTER);

        JPanel panelEnviar = new JPanel();
        JPanel panelReceber = new JPanel();

        panelMensagem.add(panelEnviar);
        panelMensagem.add(panelReceber);

        this.iniciaPanelEnviar(panelEnviar);
        this.iniciaPanelReceber(panelReceber);


    }

    private void iniciaPanelReceber(JPanel panelReceber) {
        panelReceber.setLayout(new BorderLayout());
        panelReceber.setBackground(Color.GREEN);

        JLabel lMensagem = new JLabel("Mensagens recebida:");
        panelReceber.add(lMensagem, BorderLayout.NORTH);

        JLabel mensagemRecebida = new JLabel("Aguardando recebimento da mensagem");
        mensagemRecebida.setSize(new Dimension(100,100));
        panelReceber.add(mensagemRecebida, BorderLayout.CENTER);
    }

    private void iniciaPanelEnviar(JPanel panelEnviar) {
        panelEnviar.setLayout(new BorderLayout());
        panelEnviar.setBackground(Color.GREEN);

        JLabel lMensagem = new JLabel("Mensagem:");
        panelEnviar.add(lMensagem, BorderLayout.NORTH);

        this.mensagem = new JTextField();
        this.mensagem.setSize(new Dimension(100,100));
        panelEnviar.add(this.mensagem, BorderLayout.CENTER);

        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(this::mensagemEnviada);
        panelEnviar.add(enviar, BorderLayout.SOUTH);
    }

    private void mensagemEnviada(ActionEvent event) {
        try {
            this.cliente.enviaMensagem(this.mensagem.getText());
            this.mensagem.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
