package modelo;

public class Router {
	protected int x;
	protected int y;
	private int id = 0;
	protected String nombre;
	protected String paquete;
	private static int contador = 0;

	public Router(int x, int y, String nombre) {
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.id = Router.contador++;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
}
