package modelo;

public class Cable {

	protected Router routeri;
	protected Router routerf;
	protected int ping;
	private int id;
	static int contador = 0;

	public Cable(Router routeri, Router routerf, int ping) {
		this.routeri = routeri;
		this.routerf = routerf;
		this.ping = ping;
		this.id = Cable.contador++;
	}

	public int getId() {
		return id;
	}

	public Router getNodef() {
		return routerf;
	}

	public Router getNodei() {
		return routeri;
	}

	public int getPing() {
		return ping;
	}

        public void setPing(int ping) {
            this.ping = ping;
        }
        
	@Override
	public String toString() {
		return "Nodo Inicial: " + routeri.getNombre() + "| Nodo final: " + routerf.getNombre() + "| Distancia: "
				+ ping;
	}
}
