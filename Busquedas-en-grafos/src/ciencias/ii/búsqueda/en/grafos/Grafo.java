package ciencias.ii.búsqueda.en.grafos;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class Grafo {

	protected Hashtable<Nodo, ArrayList<Arista>> tabla;
	protected ArrayList<Arista> aristas;
	protected ArrayList<Nodo> nodos;

	public Grafo() {
		this.tabla = new Hashtable<Nodo, ArrayList<Arista>>();
		this.aristas = new ArrayList<Arista>();
		this.nodos = new ArrayList<Nodo>();
	}

	public void agregarNodo(Nodo nodo) {
		ArrayList<Arista> arr = new ArrayList<Arista>();
		this.tabla.put(nodo, arr);
		this.nodos.add(nodo);
	}

	public void agregarNodos(ArrayList nodos) {
		Iterator nodo = nodos.iterator();
		while (nodo.hasNext())
			this.agregarNodo((Nodo) nodo.next());
	}

	public boolean agregarArista(Arista arista) {
		if (nodos.contains(arista.nodoi) && nodos.contains(arista.nodof)) {
			ArrayList<Arista> arri = this.tabla.get(arista.nodoi);
			ArrayList<Arista> arrf = this.tabla.get(arista.nodof);
			arri.add(arista);
			arrf.add(arista);
			this.aristas.add(arista);
			return true;
		} else
			return false;
	}

	public ArrayList<Nodo> nodosCercanos(Nodo nodo) {
		ArrayList<Nodo> nodosAdyacentes = new ArrayList<Nodo>();
		for (Arista arista : aristas) {
			if (arista.nodoi.equals(nodo)) {
				nodosAdyacentes.add(arista.nodoi);
				System.out.println("el nodo: " + arista.nodof.nombre + " es adyacente");
			}
			if (arista.nodof.equals(nodo)) {
				nodosAdyacentes.add(arista.nodof);
				System.out.println("el nodo: " + arista.nodoi.nombre + " es adyacente");
			}
		}
		return nodosAdyacentes;
	}

	public ArrayList<Arista> aristasInvolucradas(Nodo nodo) {
		ArrayList<Arista> aristasInvolucradas = new ArrayList<Arista>();
		for (Arista arista : aristas) {
			if (arista.nodoi.equals(nodo)) {
				aristasInvolucradas.add(arista);
			}
			if (arista.nodof.equals(nodo)) {
				aristasInvolucradas.add(arista);
			}
		}
		return aristasInvolucradas;
	}

	public void calcularRuta(Nodo nodoInicial, Nodo nodoDestino) {
		Nodo nodoActual = nodoInicial;
		Nodo siguienteNodo = null;
		Nodo nodoAnterior = null;
		int valorAcumulado = 0;

		while (!nodoActual.equals(nodoDestino)) {
			Arista mejorArista = new Arista(null, null, 100000000);
			ArrayList<Arista> aristasInvolucradas = aristasInvolucradas(nodoActual);
			Iterator<Arista> iterator = aristasInvolucradas.iterator();

			while (iterator.hasNext()) {
				Arista arista = iterator.next();
				if (arista.nodof.equals(nodoAnterior) || arista.nodoi.equals(nodoAnterior)) {
					iterator.remove();
				} else {
					System.out.println(arista);
					System.out.print(" //Calculo " + arista.distancia + " + " + nodoActual.distanciaManhattan + " + "
							+ valorAcumulado);
					System.out.println("");

					/*
					 * Aca hay un inconveniente con la suma de la distancia manhatan, Se esta
					 * considerando todas desde el nodo actua, es decir desde la posicion
					 * inicial desde donde se miraran las opciones y no la distancia manhatan de las
					 * opciones en si, Es posible que se requiera de un par de nodos
					 * auxiliares para comparar cada par de opciones, considerando que en la primera
					 * iteración mejorArista es nula en sus nodos y luego se sobreescribe
					 * como la primera opción
					 */
					if ((arista.distancia + nodoActual.distanciaManhattan + valorAcumulado) <= (mejorArista.distancia
							+ nodoActual.distanciaManhattan + valorAcumulado)) {
						mejorArista = arista;
					}
				}
			}

			System.out.println("mejor arista: " + mejorArista.toString());
			valorAcumulado += mejorArista.distancia;
			System.out.println("Valor acumulado: " + valorAcumulado);
			if (mejorArista.nodof.equals(nodoActual)) {
				siguienteNodo = mejorArista.nodoi;
			} else {
				siguienteNodo = mejorArista.nodof;
			}
			System.out.println("---------------------------");
			nodoAnterior = nodoActual;
			nodoActual = siguienteNodo;
			System.out.println("nodo anterior: " + nodoAnterior.toString());
			System.out.println("nodo actual: " + nodoActual.toString());
		}

		//
		/*
		 * Nodo nodoAnterior = nodoInicial;
		 * Nodo nodoActual = nodoInicial;
		 * Nodo siguienteNodo = nodoActual;
		 * int valorAcumulado = 0;
		 * Arista mejorArista = null;
		 * Nodo borrable = null;
		 * ArrayList<Arista> aristasInvolucradas = new ArrayList<Arista>();
		 * ArrayList<Nodo> nodosCandidatos = new ArrayList<Nodo>();
		 * while (!nodoActual.equals(nodoDestino)) {
		 * aristasInvolucradas = aristasInvolucradas(nodoActual);
		 * List<Arista> copyList = new ArrayList<>(aristasInvolucradas);
		 * mejorArista = aristasInvolucradas.get(0);
		 * nodosCandidatos = nodosCercanos(nodoActual);
		 * for (Nodo nodo : nodosCandidatos) {
		 * if (nodo.equals(nodoAnterior)) {
		 * borrable = nodo;
		 * }
		 * }
		 * nodosCandidatos.remove(borrable);
		 * for (Arista arista : copyList) {
		 * if (nodoAnterior.equals(arista.nodoi) || nodoAnterior.equals(arista.nodof)) {
		 * aristasInvolucradas.remove(arista);
		 * } else {
		 * Nodo aux;
		 * if (arista.nodoi.equals(nodoActual)) {
		 * aux = arista.nodof;
		 * } else {
		 * aux = arista.nodoi;
		 * }
		 * if ((arista.distancia + valorAcumulado + aux.distanciaManhattan) <=
		 * mejorArista.distancia
		 * + valorAcumulado + siguienteNodo.distanciaManhattan) {
		 * siguienteNodo = aux;
		 * }
		 * }
		 * }
		 * valorAcumulado += mejorArista.distancia;
		 * nodoActual = siguienteNodo;
		 * System.out.println("nodo actual: " + nodoActual.toString());
		 * }
		 */
	}

	public int getMaximoNodoX() {
		Iterator<Nodo> nodo = nodos.iterator();
		int max = nodo.next().x;
		while (nodo.hasNext()) {
			int x = nodo.next().x;
			if (max < x)
				max = x;
		}
		return max;
	}

	public int getMaximoNodoY() {
		Iterator<Nodo> nodo = nodos.iterator();
		int max = nodo.next().y;
		while (nodo.hasNext()) {
			int y = nodo.next().y;
			if (max < y)
				max = y;
		}
		return max;
	}

	public int getMininimoNodoX() {
		Iterator<Nodo> nodo = nodos.iterator();
		int min = nodo.next().x;
		while (nodo.hasNext()) {
			int x = nodo.next().x;
			if (min > x)
				min = x;
		}
		return min;
	}

	public double getMinimoNodoY() {
		Iterator<Nodo> nodo = nodos.iterator();
		int min = nodo.next().y;
		while (nodo.hasNext()) {
			int y = nodo.next().y;
			if (min > y)
				min = y;
		}
		return min;
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		Enumeration<Nodo> nodos = this.tabla.keys();
		while (nodos.hasMoreElements()) {
			Nodo nodo = nodos.nextElement();
			s.append(nodo.toString() + "->");
			ArrayList<Arista> adyacencia = this.tabla.get(nodo);
			Iterator<Arista> it = adyacencia.iterator();
			while (it.hasNext()) {
				s.append(it.next().toString());
				if (it.hasNext())
					s.append("-");
			}
			s.append("\n");
		}
		return new String(s);
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	public ArrayList<Nodo> getNodos() {
		return nodos;
	}

	public Hashtable<Nodo, ArrayList<Arista>> getTabla() {
		return tabla;
	}

}
