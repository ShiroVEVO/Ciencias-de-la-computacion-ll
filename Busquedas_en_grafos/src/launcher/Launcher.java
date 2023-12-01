package launcher;

import control.busqueda.BusquedaA;
import control.Controlador;
import java.io.IOException;
import modelo.Grafo;
import vista.interfaz.Mapa_colombia;
import vista.interfaz.SelectorMunicipio;

public class Launcher {

	public static void main(String[] args) throws IOException {
            BusquedaA a_Asterisco = new BusquedaA();
            Grafo grafo = new Grafo();
            Mapa_colombia mapa = new Mapa_colombia();
            SelectorMunicipio selector = new SelectorMunicipio();
            
            Controlador c = new Controlador(a_Asterisco, grafo, mapa, selector);
            c.iniciar();
	}

}
