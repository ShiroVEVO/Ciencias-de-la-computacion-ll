package ciencias.ii.búsqueda.en.grafos;

public class Arista {

	protected Nodo nodoi;
	protected Nodo nodof;
	protected int distancia;
	protected int id;
	static int Cont = 0;

	public Arista(Nodo nodoi, Nodo nodof, int distancia) {
		this.nodoi = nodoi;
		this.nodof = nodof;
		this.distancia = distancia;
		this.id = ++Arista.Cont;
	}

	public int getId() {
		return id;
	}

	public Nodo getNodef() {
		return nodof;
	}

	public Nodo getNodei() {
		return nodoi;
	}

	public int getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		return "Arista: " + this.id + " " + distancia + " [" + this.nodoi.toString() + "-" + this.nodof.toString()
				+ "]";
	}
}
