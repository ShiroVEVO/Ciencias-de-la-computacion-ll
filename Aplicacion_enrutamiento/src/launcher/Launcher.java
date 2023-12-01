package launcher;

import control.Topologia;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import modelo.Red;
import vista.interfaz.InterfazConsola;
import vista.interfaz.RedComunicacion;

public class Launcher {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {
            Red grafo = new Red();
            RedComunicacion simulacion = new RedComunicacion();
            InterfazConsola consola = new InterfazConsola();
            
            Topologia bipartita = new Topologia(grafo, simulacion, consola);
            bipartita.iniciar();
	}

}
