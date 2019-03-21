package jantarDosFilosofos;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Criando os filosofos
        Filosofo f1 = new Filosofo("Prea");
        Filosofo f2 = new Filosofo("Paje");
        Filosofo f3 = new Filosofo("Mesquita");
        Filosofo f4 = new Filosofo("Tulin");
        Filosofo f5 = new Filosofo("Denis DJ");

        //Adicionando os filosofos à lista
        ArrayList<Filosofo> filosofos = new ArrayList();
        filosofos.add(f1);
        filosofos.add(f2);
        filosofos.add(f3);
        filosofos.add(f4);
        filosofos.add(f5);

        //Criando a mesa de jantar e posicionando os filosofos
        MesaDeJantar mesa = new MesaDeJantar(filosofos);

        Interface interfaceGrafica = new Interface(mesa);

        //Imprimir situação da mesa e atualiza interface
        while(true) {
            mesa.printarSituaçãoMesa();
            interfaceGrafica.atualiza();
            try {
                Thread.currentThread().sleep(550);
            }catch(InterruptedException e) {
                System.out.println("Erro ao imprimir LOGs da Mesa de Jantar");
            }


        }
    }
}
