package cliente;

import server.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {

    private Socket cliente;

    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public synchronized void run() {
        try {
            this.connect();
            this.identificar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws IOException {
        this.cliente = new Socket(Server.getServerIp(), Server.getServerPort());
    }

    private void identificar() throws IOException {
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(this.nome + " conectou");
        saida.close();
        this.cliente.close();
    }

    public void enviaMensagem(String mensagem) throws IOException {
        this.connect();
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(this.nome + " disse: " + mensagem);
        saida.close();
        this.cliente.close();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
