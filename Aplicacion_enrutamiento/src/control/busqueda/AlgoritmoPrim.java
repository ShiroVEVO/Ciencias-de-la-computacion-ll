package control.busqueda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import modelo.Cable;
import modelo.Red;
import modelo.Router;

public class AlgoritmoPrim implements Busqueda {

    @Override
    public ArrayList<Cable> obtenerRuta(Red g, Router nodoInicial, Router nodoDestino) {
        ArrayList<Cable> rutaMinima = new ArrayList<>();
        HashSet<Router> visitados = new HashSet<>();
        visitados.add(nodoInicial);

        PriorityQueue<Cable> aristasCandidatas = new PriorityQueue<>(
                (a1, a2) -> Integer.compare(a1.getPing(), a2.getPing()));
        aristasCandidatas.addAll(g.getTabla().get(nodoInicial));

        while (!aristasCandidatas.isEmpty()) {
            Cable aristaActual = aristasCandidatas.poll();
            Router nodo1 = aristaActual.getNodei();
            Router nodo2 = aristaActual.getNodef();

            if (!visitados.contains(nodo1) || !visitados.contains(nodo2)) {
                rutaMinima.add(aristaActual);
                Router siguienteNodo = visitados.contains(nodo1) ? nodo2 : nodo1;
                visitados.add(siguienteNodo);
                aristasCandidatas.addAll(g.getTabla().get(siguienteNodo));
            }
        }

        return rutaMinima;
    }
}
