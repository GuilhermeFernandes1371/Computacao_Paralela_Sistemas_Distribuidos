package server;

import cliente.Cliente;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class InterfaceServer {

    private JFrame frame;
    private JTextArea textArea;

    public InterfaceServer() {
        this.frame = new JFrame("Server");
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500,500);
        this.frame.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        this.frame.add(panelPrincipal);

        this.iniciaMenu(panelPrincipal);
        this.iniciaCliente(panelPrincipal);
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

    private void iniciaCliente(JPanel panelPrincipal) {
        JPanel panelCliente = new JPanel();

        panelCliente = new JPanel();
        panelCliente.setLayout(new BorderLayout());
        panelCliente.setBackground(Color.GREEN);

        JLabel label = new JLabel("Mensagem do Servidor");
        panelCliente.add(label, BorderLayout.NORTH);

        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        panelCliente.add(this.textArea, BorderLayout.CENTER);

        panelPrincipal.add(panelCliente, BorderLayout.CENTER);
    }

    public void novaMensagem(String mensagem) {
        this.textArea.append(mensagem);
    }

}
