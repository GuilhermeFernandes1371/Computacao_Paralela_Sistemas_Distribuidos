package main;

import cliente.Cliente;
import cliente.InterfaceCliente;
import server.InterfaceServer;
import server.Server;

public class Main {

    public static void main(String[] args) {
        InterfaceServer interfaceServer = new InterfaceServer();
        Server server = new Server(interfaceServer);
        server.start();

        InterfaceMain newClientes = new InterfaceMain();
    }

}
