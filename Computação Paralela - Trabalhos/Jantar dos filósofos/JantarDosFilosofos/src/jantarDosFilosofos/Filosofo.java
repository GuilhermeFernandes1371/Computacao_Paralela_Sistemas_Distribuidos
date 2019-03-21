package jantarDosFilosofos;

public class Filosofo extends Thread {
    private String nome;
    private int tentativasDeComer;

    /* filósofo pensando */
    final static int PENSANDO = 1;
    /* filósofo comendo */
    final static int COMENDO = 2;
    /* filósofo com fome */
    final static int COM_FOME = 3;

    //Se o filosofo está PENSANDO, COMENDO ou COM_FOME
    private int estado;

    /* Tempo máximo (em milesegundos) que o filósofo vai comer ou pensar */
    final static int TEMPO_MAXIMO = 1000;

    //Lugar da mesa onde o filosofo está posicionado
    private Lugares lugar;

    public Filosofo (String nomeThread) {
        /* construtor da classe pai */
        super(nomeThread);
        this.nome = nomeThread;
    }

    public void run () {
        int tempo = 0;
        /* Laço representando a vida de um filósofo : pensar e comer */
        while (true) {
            /* sorteando o tempo pelo qual o filósofo vai pensar */
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            /* filósofo pensando */
            pensar(tempo);
            /* filósofo pegando garfos */
            tentarPegarGarfos();
            /* sorteando o tempo pelo qual o filósofo vai comer */
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            /* filósofo comendo */
            comer(tempo);
            /* filósofo devolvendo garfos */
            devolverGarfos();
        }
    }

    /* simula o filósofo pensando */
    private void pensar (int tempo) {
        this.estado = PENSANDO;
        try {
            //System.out.println(this.nome + " começou a pensar");
            /* filósofo dorme de tempo milisegundos */
            //System.out.println(this.nome + " pensando ");
            sleep(tempo);
        } catch (InterruptedException e){
            System.out.println("Filófoso pensou demais, morreu =) ");
        }
    }
    /* simula o filósofo comendo */
    private void comer (int tempo) {
        this.estado = COMENDO;

        this.pegarGarfos();
        try {
            //System.out.println(this.nome + " começou a comer");
            sleep(tempo);
        } catch (InterruptedException e){
            System.out.println("Filófoso comeu demais, morreu");
        }
    }
    /* pega os garfos */
    private synchronized void tentarPegarGarfos() {
        this.estado = COM_FOME;

        while (this.lugar.getEsquerda().getFilosofo().getEstado() == COMENDO
                || this.lugar.getDireita().getFilosofo().getEstado() == COMENDO) {
            try {
                //System.out.println(this.nome + " tentou comer");
                /* Filósofo tentou comer e não conseguiu */
                this.tentativasDeComer++;

                /* colocando o filosofo para esperar */
                this.wait(600);
            } catch (InterruptedException e) {
                System.out.println("Filósofo morreu de fome");
            }
        }
    }

    public void pegarGarfos() {
        this.lugar.setGarfoEsquerdo(true);
        this.lugar.setGarfoDireita(true);

        this.lugar.getDireita().setGarfoEsquerdo(true);
        this.lugar.getEsquerda().setGarfoDireita(true);
    }

    /* devolve os garfos */
    public synchronized void devolverGarfos() {
        this.lugar.setGarfoEsquerdo(false);
        this.lugar.setGarfoDireita(false);

        this.lugar.getDireita().setGarfoEsquerdo(false);
        this.lugar.getEsquerda().setGarfoDireita(false);

        this.notifyAll();
    }

    public void setLugar(Lugares lugar) {
        this.lugar = lugar;
    }

    public Lugares getLugar() {
        return this.lugar;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static String imprimeEstadosFilosofos (int estado) {
        switch (estado) {
            case PENSANDO :
                return "PENSANDO";
            case COM_FOME :
                return "COM_FOME";
            case COMENDO :
                return "COMENDO";
        }
        return "ERRO";
    }
}