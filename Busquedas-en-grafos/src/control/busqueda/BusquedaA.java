package control.busqueda;

import control.busqueda.Busqueda;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;
import vista.interfaz.Consola;

public class BusquedaA implements Busqueda {

    long tiempoLimite;
    private Consola consola = new Consola();
    
    public ArrayList<Arista> aristasInvolucradas(Grafo g, Nodo nodo) {
        ArrayList<Arista> aristasInvolucradas = new ArrayList<>();
        for (Arista arista : g.getAristas()) {
            if (arista.getNodei().equals(nodo)) {
                aristasInvolucradas.add(arista);
            }
            if (arista.getNodef().equals(nodo)) {
                aristasInvolucradas.add(arista);
            }
        }
        return aristasInvolucradas;
    }

    @Override
    public ArrayList<Arista> obtenerRuta(Grafo g, Nodo nodoInicial, Nodo nodoDestino) {
        consola.setVisible(true);
        tiempoLimite = System.currentTimeMillis();
        
        ArrayList<Arista> caminoSolucion = new ArrayList<>();
        Nodo nodoActual = nodoInicial;
        Nodo siguienteNodo = null;
        Nodo nodoAnterior = null;
        int valorAcumulado = 0;

        while (!nodoActual.equals(nodoDestino)) {
            Arista mejorArista = null;
            ArrayList<Arista> aristasInvolucradas = aristasInvolucradas(g, nodoActual);
            Iterator<Arista> iterator = aristasInvolucradas.iterator();
            while (iterator.hasNext()) {
                Arista arista = iterator.next();
                if (arista.getNodef().equals(nodoAnterior) || arista.getNodei().equals(nodoAnterior)) {
                    iterator.remove();
                }
            }
            iterator = aristasInvolucradas.iterator();

            while (iterator.hasNext()) {
                Arista arista = iterator.next();
                Nodo aux, aux2;
                mejorArista = (mejorArista == null) ? arista : mejorArista;
                if (arista.getNodef() == nodoActual) {
                    aux = arista.getNodei();
                    consola.imprimir("---Nodo Aux: " + aux.toString());
                } else {
                    aux = arista.getNodef();
                    consola.imprimir("---Nodo Aux: " + aux.toString());
                }
                if (mejorArista.getNodef() == nodoActual) {
                    aux2 = mejorArista.getNodei();
                    consola.imprimir("---Nodo Aux2: " + aux2.toString());
                } else {
                    aux2 = mejorArista.getNodef();
                    consola.imprimir("---Nodo Aux2: " + aux2.toString());
                }
                consola.imprimir(
                        " //Calculo arista: " + arista.getDistancia() + " + " + aux.getDistanciaManhattan() + " + "
                                + valorAcumulado + " = "
                                + (arista.getDistancia() + aux.getDistanciaManhattan() + valorAcumulado));
                consola.imprimir(
                        " //Calculo aristaA: " + mejorArista.getDistancia() + " + " + aux2.getDistanciaManhattan()
                                + " + " + valorAcumulado + " = "
                                + (mejorArista.getDistancia() + aux2.getDistanciaManhattan() + valorAcumulado));
                if ((arista.getDistancia() + aux.getDistanciaManhattan()
                        + valorAcumulado) <= (mejorArista.getDistancia()
                                + aux2.getDistanciaManhattan() + valorAcumulado)) {
                    mejorArista = arista;
                    consola.imprimir("Posible mejor: " + mejorArista.toString());
                } else {
                    consola.imprimir("Se mantiene la mejor arista anterior");
                }
            }

            caminoSolucion.add(mejorArista);
            consola.imprimir("mejor arista: " + mejorArista.toString());
            valorAcumulado += mejorArista.getDistancia();
            consola.imprimir("Valor acumulado: " + valorAcumulado);
            if (mejorArista.getNodef().equals(nodoActual)) {
                siguienteNodo = mejorArista.getNodei();
            } else {
                siguienteNodo = mejorArista.getNodef();
            }
            consola.imprimir("---------------------------");
            nodoAnterior = nodoActual;
            nodoActual = siguienteNodo;
            consola.imprimir("nodo anterior: " + nodoAnterior.toString());
            consola.imprimir("nodo actual: " + nodoActual.toString());
            
            if(System.currentTimeMillis() - tiempoLimite > 500) {
                return null;
            }
        }
        return caminoSolucion;
    }

}