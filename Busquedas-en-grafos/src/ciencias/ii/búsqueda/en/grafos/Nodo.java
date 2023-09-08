package ciencias.ii.búsqueda.en.grafos;

public class Nodo {
	protected int x;
	protected int y;
	protected int id;
	protected String nombre;
	protected int distanciaManhattan;
	static int Cont = 0;

	public Nodo(int x, int y, String nombre, int distanciaManhattan) {
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.distanciaManhattan = distanciaManhattan;
		this.id = ++Nodo.Cont;
	}

	public Nodo() {
		this(0, 0);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDistanciaManhattan() {
		return distanciaManhattan;
	}

	@Override
	public String toString() {
		return "Nodo:" + this.id + "(" + this.x + "," + this.y + ")" + ", " + this.nombre + ", "
				+ this.distanciaManhattan;
	}

	/*
	 * public NodePanel getNodePanel(){
	 * return new NodePanel(this);
	 * }
	 */

}
