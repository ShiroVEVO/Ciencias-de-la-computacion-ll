package control.busqueda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import modelo.Cable;
import modelo.Red;
import modelo.Router;

public class AlgoritmoKruskal implements Busqueda {

    @Override
    public ArrayList<Cable> obtenerRuta(Red g, Router nodoInicial, Router nodoFinal) {
        ArrayList<Cable> rutaMinima = new ArrayList<>();
        ArrayList<Cable> todasAristas = new ArrayList<>(g.getCables());
        HashMap<Router, Router> papa = new HashMap<>();

        Collections.sort(todasAristas, Comparator.comparingInt(a -> a.getPing()));
        for (Router nodo : g.getNodos()) {
            papa.put(nodo, nodo);
        }

        for (Cable arista : todasAristas) {
            Router nodo1 = arista.getNodei();
            Router nodo2 = arista.getNodef();
            Router papaNodo1 = buscarPapa(papa, nodo1);
            Router papaNodo2 = buscarPapa(papa, nodo2);

            if (papaNodo1 != papaNodo2) {
                rutaMinima.add(arista);
                papa.put(papaNodo1, papaNodo2);
            }
        }
        return rutaMinima;
    }

    private Router buscarPapa(HashMap<Router, Router> papa, Router nodo) {
        if (papa.get(nodo) == nodo) {
            return nodo;
        }
        papa.put(nodo, buscarPapa(papa, papa.get(nodo)));
        return papa.get(nodo);
    }

    // Arista de menor peso?
    // Siguiente arista? y siguiente y siguiente...
    // Hay pesos iguales? Si lo hay entonces se toma la que no forma ciclo
    // Repetir hasta que todos los vertices esten tocados por alguna arista
}
