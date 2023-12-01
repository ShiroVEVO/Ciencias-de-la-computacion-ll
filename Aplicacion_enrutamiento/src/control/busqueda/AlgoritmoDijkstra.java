/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.busqueda;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import modelo.Cable;
import modelo.Red;
import modelo.Router;

/**
 *
 * @author amirz
 */
public class AlgoritmoDijkstra implements Busqueda {

    @Override
    public ArrayList<Cable> obtenerRuta(Red g, Router nodoInicial, Router nodoDestino) {
        LinkedHashMap<Router, Integer> pasoDijkstra = new LinkedHashMap<>();
        HashMap<Router, Cable> aristasCandidatas = new HashMap<>();
        ArrayList<Integer> continuaCamino = new ArrayList<>();
        ArrayList<Cable> arbolSolucion = new ArrayList<>();
        ArrayList<Cable> aristasNodo;

        Cable aristaCandidata;
        Router nodoCandidato;
        Router nodoActual = nodoInicial;
        int valorMinimo;
        int minimo = 0;
        int auxPosicion;
        int posicion = 0;
        int minimoAnterior = 0;

        for (Router nodo : g.getNodos()) {
            pasoDijkstra.put(nodo, Integer.MAX_VALUE);
            aristasCandidatas.put(nodo, null);
        }
        pasoDijkstra.replace(nodoActual, null);

        while (nodoActual != nodoDestino) {
            valorMinimo = Integer.MAX_VALUE;
            auxPosicion = 0;
            aristasNodo = g.getTabla().get(nodoActual);

            for (int i = 0; i < aristasNodo.size(); i++) {
                aristaCandidata = aristasNodo.get(i);

                // Verifica cuál nodo es el proximo de la arista
                if (aristaCandidata.getNodef() == nodoActual) {
                    nodoCandidato = aristaCandidata.getNodei();
                } else {
                    nodoCandidato = aristaCandidata.getNodef();
                }

                if (pasoDijkstra.get(nodoCandidato) != null) {
                    pasoDijkstra.replace(nodoCandidato, minimo + aristaCandidata.getPing());
                    continuaCamino.add(nodoCandidato.getId());
                    aristasCandidatas.replace(nodoCandidato, aristaCandidata);
                }
            }

            for (Integer valor : pasoDijkstra.values()) {
                if (valor != null) {
                    if (valor < valorMinimo) {
                        valorMinimo = valor;
                        posicion = auxPosicion;
                    }
                }
                auxPosicion++;
            }

            if (continuaCamino.contains(posicion)) {
                minimo = minimo + valorMinimo;
            } else {
                minimo = minimo - minimoAnterior + valorMinimo;
            }
            arbolSolucion.add(
                    aristasCandidatas.get(g.getNodos().get(posicion)));

            minimoAnterior = minimo;
            continuaCamino.clear();
            pasoDijkstra.replace(g.getNodos().get(posicion), null);
            nodoActual = g.getNodos().get(posicion);
        }

        return podarArbol(arbolSolucion, nodoDestino, nodoInicial);
    }

    private ArrayList<Cable> podarArbol(ArrayList<Cable> arbolSolucion, Router nodoDestino, Router nodoInicial) {
        ArrayList<Cable> caminoSolucion = new ArrayList<>();
        int historialArista = 0;
        int i = arbolSolucion.size() - 2;
        Router nodoCamino;
        Router nodoAnterior = null;

        if (arbolSolucion.get(arbolSolucion.size() - 1).getNodef() == nodoDestino) {
            nodoCamino = arbolSolucion.get(arbolSolucion.size() - 1).getNodei();
        } else {
            nodoCamino = arbolSolucion.get(arbolSolucion.size() - 1).getNodef();
        }

        caminoSolucion.add(arbolSolucion.get(arbolSolucion.size() - 1));
        while (nodoCamino != nodoInicial) {
            if (nodoCamino == arbolSolucion.get(i).getNodef() ||
                    nodoCamino == arbolSolucion.get(i).getNodei()) {
                caminoSolucion.add(arbolSolucion.get(i));
                historialArista = i;

                nodoAnterior = nodoCamino;
                if (arbolSolucion.get(i).getNodef() == nodoCamino) {
                    nodoCamino = arbolSolucion.get(i).getNodei();
                } else {
                    nodoCamino = arbolSolucion.get(i).getNodef();
                }
            }
            if (i == 0 && nodoCamino != nodoInicial) {
                arbolSolucion.remove(historialArista);
                caminoSolucion.remove(caminoSolucion.size() - 1);
                nodoCamino = nodoAnterior;
                i = historialArista;
            }
            i--;
        }

        return caminoSolucion;
    }
}