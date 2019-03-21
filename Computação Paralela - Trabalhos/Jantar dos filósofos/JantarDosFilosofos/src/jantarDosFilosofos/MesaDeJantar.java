package jantarDosFilosofos;

        import java.util.ArrayList;

public class MesaDeJantar {

    private ArrayList<Lugares> lugares = new ArrayList();

    public ArrayList<Lugares> getLugares() {
        return this.lugares;
    }

    public MesaDeJantar(ArrayList<Filosofo> filosofos) {

        //Posicionando filosofos na mesa
        int posicao=0;
        for (Filosofo filosofo : filosofos) {
            Lugares lugar = new Lugares(filosofo, posicao);
            this.lugares.add(lugar);
            filosofo.setLugar(lugar);
            posicao++;
        }

        //Definindo vizinhos dos lugares
        for (Lugares lugar : this.lugares) {

            //Primeiro lugar da mesa que fica ao lado do ultimo
            if (lugar.getPosicao() == 0) {
                lugar.setEsquerda(this.lugares.get(this.lugares.size()-1));
                lugar.setDireita(this.lugares.get(1));
            }

            //Ultimo lugar da mesa que fica ao lado do primeiro
            else if (lugar.getPosicao() == (this.lugares.size() -1)) {
                lugar.setEsquerda(this.lugares.get(this.lugares.size()-2));
                lugar.setDireita(this.lugares.get(0));
            }

            //Caso eles estejam no meio
            else {
                lugar.setEsquerda(this.lugares.get(lugar.getPosicao() -1));
                lugar.setDireita(this.lugares.get(lugar.getPosicao() +1));
            }
        }

        System.out.println("Mesa criada!");

        //Iniciar a concorrencia dos filosofos
        for (Filosofo filosofo : filosofos) {
            filosofo.start();
        }
    }

    public void add(Filosofo filosofo) {
        Lugares primeiro = this.lugares.get(0);
        Lugares ultimo = this.lugares.get(this.lugares.size() -1);

        Lugares novo = new Lugares(filosofo, ultimo.getPosicao() + 1);
        this.lugares.add(novo);
        filosofo.setLugar(novo);

        primeiro.setEsquerda(novo);
        ultimo.setDireita(novo);

        novo.setDireita(primeiro);
        novo.setEsquerda(ultimo);

        filosofo.start();
    }

    public void printarSituaçãoMesa() {
        System.out.println("---ESTADO-MESA---");
        for (Lugares lugar : this.lugares) {
            System.out.println("Lugar: " + lugar.getPosicao() + " (" + lugar.getFilosofo().getNome() + ")");
            System.out.println("Esquerda: " + lugar.getEsquerda().getFilosofo().getNome() + " - Direita: " + lugar.getDireita().getFilosofo().getNome());
            System.out.println("Estado: " + Filosofo.imprimeEstadosFilosofos(lugar.getFilosofo().getEstado()));
        }
        System.out.println();
    }

}
