package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread {

    private static int SERVER_PORT = 3031;
    private static String SERVER_IP = "127.0.0.1";

    private ServerSocket server;
    private ArrayList<Socket> listaClientes;
    private InterfaceServer interfaceServer;

    public Server(InterfaceServer InterfaceServer) {
        this.interfaceServer = InterfaceServer;
        try {
            this.iniciaServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void run() {
        try {
            while(true) {
                this.verificaClientes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void iniciaServer() throws IOException {
        this.listaClientes = new ArrayList<>();
        this.server = new ServerSocket(this.SERVER_PORT);
    }

    public void verificaClientes() throws IOException {
        Socket cliente = this.server.accept();
        this.listaClientes.add(cliente);
        this.interfaceServer.novaMensagem(this.lerEntradaCliente(cliente) + "\n");
        cliente.close();
    }

    public String lerEntradaCliente(Socket cliente) throws IOException {
        Scanner entrada = new Scanner(cliente.getInputStream());
        return entrada.nextLine();
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }

    public static String getServerIp() {
        return SERVER_IP;
    }
}

