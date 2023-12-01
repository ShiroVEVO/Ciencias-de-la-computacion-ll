package control.busqueda;

import java.util.ArrayList;
import modelo.Cable;
import modelo.Red;
import modelo.Router;

public interface Busqueda {
    public ArrayList<Cable> obtenerRuta(Red g, Router nodoInicial, Router nodoDestino);
}
