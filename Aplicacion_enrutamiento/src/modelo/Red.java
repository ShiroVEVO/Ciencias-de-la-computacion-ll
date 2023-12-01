package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Red {

	protected HashMap<Router, ArrayList<Cable>> tabla;
	protected ArrayList<Cable> Cables;
	protected ArrayList<Router> routers;

	public Red() {
		this.tabla = new HashMap<>();
		this.Cables = new ArrayList<>();
		this.routers = new ArrayList<>();
	}

	public void agregarNodo(Router nodo) {
		ArrayList<Cable> arr = new ArrayList<>();
		this.tabla.put(nodo, arr);
		this.routers.add(nodo);
	}

	public boolean agregarCable(Cable cable) {
		if (routers.contains(cable.routeri) && routers.contains(cable.routerf)) {
			ArrayList<Cable> arri = this.tabla.get(cable.routeri);
			ArrayList<Cable> arrf = this.tabla.get(cable.routerf);
			arri.add(cable);
			arrf.add(cable);
			this.Cables.add(cable);
			return true;
		} else
			return false;
	}
        
        public void removerCable(Cable cable) {
            Cables.remove(cable);
        }

	public ArrayList<Cable> getCables() {
		return Cables;
	}

	public ArrayList<Router> getNodos() {
		return routers;
	}

	public HashMap<Router, ArrayList<Cable>> getTabla() {
		return tabla;
	}
}