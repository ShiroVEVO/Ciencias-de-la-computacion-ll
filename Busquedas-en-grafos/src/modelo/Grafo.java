package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

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
