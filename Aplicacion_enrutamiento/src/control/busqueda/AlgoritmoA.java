/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.busqueda;

import java.util.ArrayList;
import modelo.Cable;
import modelo.Red;
import modelo.Router;

/**
 *
 * @author amirz
 */
public class AlgoritmoA implements Busqueda {

    @Override
    public ArrayList<Cable> obtenerRuta(Red g, Router nodoInicial, Router nodoDestino) {
        ArrayList<Cable> aristasNodo;
        ArrayList<Cable> caminoSolucion = new ArrayList<>();

        Router nodoActual = nodoInicial;
        Router nodoAnterior = null;
        Router nodoProximo;
        Cable aristaCandidata;

        int funcionCosto = 0;
        int costoAcumulado = 0;
        int auxCosto = 0;
        boolean remover = false;

        while (nodoActual != nodoDestino) {
            aristasNodo = g.getTabla().get(nodoActual);

            for (int i = 0; i < aristasNodo.size(); i++) {
                aristaCandidata = aristasNodo.get(i);

                // Verifica cuál nodo es el proximo de la arista
                if (aristaCandidata.getNodef() == nodoActual) {
                    nodoProximo = aristaCandidata.getNodei();
                } else {
                    nodoProximo = aristaCandidata.getNodef();
                }

                // Vecindades
                if (nodoProximo != nodoDestino) {
                    auxCosto = aristaCandidata.getPing() +
                            distanciaEuclideana(nodoProximo, nodoDestino);
                } else {
                    costoAcumulado = costoAcumulado + aristaCandidata.getPing();
                    if (remover) {
                        caminoSolucion.remove(caminoSolucion.size() - 1);
                    }
                    caminoSolucion.add(aristaCandidata);
                    return caminoSolucion;
                }

                // Apilamiento de aristas solución
                if (auxCosto < funcionCosto || funcionCosto == 0) {
                    if (nodoAnterior != nodoProximo) {
                        funcionCosto = auxCosto;
                        if (remover) {
                            caminoSolucion.remove(caminoSolucion.size() - 1);
                        }
                        caminoSolucion.add(aristaCandidata);
                        remover = true;
                    }
                }
            }

            nodoAnterior = nodoActual;
            if (nodoAnterior == caminoSolucion.get(caminoSolucion.size() - 1).getNodef()) {
                nodoActual = caminoSolucion.get(caminoSolucion.size() - 1).getNodei();
            } else {
                nodoActual = caminoSolucion.get(caminoSolucion.size() - 1).getNodef();
            }

            if (nodoActual == nodoInicial) {
                return null;
            }

            costoAcumulado = costoAcumulado + funcionCosto;
            funcionCosto = 0;
            remover = false;
        }

        return caminoSolucion;
    }

    private int distanciaEuclideana(Router nodo1, Router nodo2) {
        int x1 = nodo1.getX();
        int y1 = nodo1.getY();
        int x2 = nodo2.getX();
        int y2 = nodo2.getY();

        int potenciaX = (x2 - x1) * (x2 - x1);
        int potenciaY = (y2 - y1) * (y2 - y1);

        int distanciaEuclideana = (int) Math.sqrt(potenciaX + potenciaY);

        return distanciaEuclideana;
    }
}