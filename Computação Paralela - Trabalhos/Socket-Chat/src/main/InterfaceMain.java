package main;

import cliente.Cliente;
import cliente.InterfaceCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfaceMain {

    private JFrame frame;
    private JTextField nome;

    public InterfaceMain() {
        this.frame = new JFrame("Criador de clientes");
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400,100);
        this.frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.add(panel);

        JLabel clienteNome = new JLabel("Digite o nome");
        this.nome = new JTextField();
        JButton novoCliente = new JButton("Novo Cliente");
        novoCliente.addActionListener(this::novoCliente);

        panel.add(clienteNome,BorderLayout.NORTH);
        panel.add(this.nome, BorderLayout.CENTER);
        panel.add(novoCliente, BorderLayout.SOUTH);
    }

    private void novoCliente(ActionEvent event) {
        Cliente cliente = new Cliente(this.nome.getText());
        InterfaceCliente interfaceCliente = new InterfaceCliente(cliente);
        cliente.start();
    }
}
