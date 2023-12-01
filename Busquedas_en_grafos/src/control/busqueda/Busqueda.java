package control.busqueda;

import java.util.ArrayList;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

public interface Busqueda {
    public ArrayList<Arista> obtenerRuta(Grafo g, Nodo nodoInicial, Nodo nodoDestino);
}
