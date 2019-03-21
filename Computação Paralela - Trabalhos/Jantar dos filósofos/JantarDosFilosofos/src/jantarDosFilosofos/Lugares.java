package jantarDosFilosofos;

public class Lugares {
    private int posicao;
    private Lugares direita;
    private Lugares esquerda;
    private boolean garfoEsquerdo;
    private boolean garfoDireita;
    private Filosofo filosofo;

    public Lugares(Filosofo filosofo,int posicao) {
        this.filosofo = filosofo;
        this.garfoDireita = false;
        this.garfoEsquerdo = false;
        this.posicao = posicao;
    }

    public Filosofo getFilosofo() {
        return this.filosofo;
    }

    public Lugares getDireita() {
        return this.direita;
    }

    public void setDireita(Lugares lugarDir) {
        this.direita = lugarDir;
    }

    public Lugares getEsquerda() {
        return this.esquerda;
    }

    public void setEsquerda(Lugares lugarEsq) {
        this.esquerda = lugarEsq;
    }

    public int getPosicao() { return this.posicao; }

    public boolean getGarfoEsquerdo() {
        return this.garfoEsquerdo;
    }

    public void setGarfoEsquerdo(boolean garfoEsquerdo) {
        this.garfoEsquerdo = garfoEsquerdo;
    }

    public boolean getGarfoDireita() {
        return this.garfoDireita;
    }

    public void setGarfoDireita(boolean garfoDireita) {
        this.garfoDireita = garfoDireita;
    }

}
