package control;

import control.busqueda.AlgoritmoDijkstra;
import control.busqueda.Busqueda;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import modelo.Cable;
import modelo.Red;
import modelo.Router;
import vista.interfaz.InterfazConsola;
import vista.interfaz.RedComunicacion;

public class Topologia {
    private Busqueda busqueda;
    private final Red grafo;
    private final RedComunicacion simulacion;
    private final Random rand;
    private final InterfazConsola consola;
    private int intentosMaximos = 0;
    private static int RETARDO_INTERNET = 5;
    
    public Topologia(Red grafo, RedComunicacion simulacion, InterfazConsola consola) throws NoSuchAlgorithmException {
        this.rand = SecureRandom.getInstanceStrong();
        this.grafo = grafo;
        this.simulacion = simulacion;
        this.consola = consola;
        this.busqueda = new AlgoritmoDijkstra();
    }
    
    public void iniciar() throws IOException, InterruptedException {
        Router router0 = new Router(130, 350, generarDireccionesIP());
        Router router1 = new Router(315, 350, generarDireccionesIP());
        Router router2 = new Router(315, 150, generarDireccionesIP());
        Router router3 = new Router(315, 550, generarDireccionesIP());
        Router router4 = new Router(550, 50, generarDireccionesIP());
        Router router5 = new Router(550, 250, generarDireccionesIP());
        Router router6 = new Router(550, 450, generarDireccionesIP());
        Router router7 = new Router(550, 650, generarDireccionesIP());
        Router router8 = new Router(835, 50, generarDireccionesIP());
        Router router9 = new Router(835, 250, generarDireccionesIP());
        Router router10 = new Router(835, 450, generarDireccionesIP());
        Router router11 = new Router(835, 650, generarDireccionesIP());
        Router router12 = new Router(1085, 200, generarDireccionesIP());
        Router router13 = new Router(1085, 510, generarDireccionesIP());
        Router router14 = new Router(1245, 350, generarDireccionesIP());

        grafo.agregarNodo(router0);
        grafo.agregarNodo(router1);
        grafo.agregarNodo(router2);
        grafo.agregarNodo(router3);
        grafo.agregarNodo(router4);
        grafo.agregarNodo(router5);
        grafo.agregarNodo(router6);
        grafo.agregarNodo(router7);
        grafo.agregarNodo(router8);
        grafo.agregarNodo(router9);
        grafo.agregarNodo(router10);
        grafo.agregarNodo(router11);
        grafo.agregarNodo(router12);
        grafo.agregarNodo(router13);
        grafo.agregarNodo(router14);

        // Cables entre Conjunto 1 y Conjunto 2
        Cable Cable01_1 = new Cable(router0, router1, (int) (distribucionNormal()));
        Cable Cable01_2 = new Cable(router0, router2, (int) (distribucionNormal()));
        Cable Cable01_3 = new Cable(router0, router3, (int) (distribucionNormal()));

        // Cables entre Conjunto 2 y Conjunto 3
        Cable Cable23_1 = new Cable(router1, router4, (int) (distribucionNormal()));
        Cable Cable23_2 = new Cable(router1, router5, (int) (distribucionNormal()));
        Cable Cable23_3 = new Cable(router1, router6, (int) (distribucionNormal()));
        Cable Cable23_4 = new Cable(router1, router7, (int) (distribucionNormal()));
        Cable Cable23_5 = new Cable(router2, router4, (int) (distribucionNormal()));
        Cable Cable23_6 = new Cable(router2, router5, (int) (distribucionNormal()));
        Cable Cable23_7 = new Cable(router2, router6, (int) (distribucionNormal()));
        Cable Cable23_8 = new Cable(router2, router7, (int) (distribucionNormal()));
        Cable Cable23_9 = new Cable(router3, router4, (int) (distribucionNormal()));
        Cable Cable23_10 = new Cable(router3, router5, (int) (distribucionNormal()));
        Cable Cable23_11 = new Cable(router3, router6, (int) (distribucionNormal()));
        Cable Cable23_12 = new Cable(router3, router7, (int) (distribucionNormal()));

        // Cables entre Conjunto 3 y Conjunto 4
        Cable Cable34_1 = new Cable(router4, router8, (int) (distribucionNormal()));
        Cable Cable34_2 = new Cable(router4, router9, (int) (distribucionNormal()));
        Cable Cable34_3 = new Cable(router4, router10, (int) (distribucionNormal()));
        Cable Cable34_4 = new Cable(router4, router11, (int) (distribucionNormal()));
        Cable Cable34_5 = new Cable(router5, router8, (int) (distribucionNormal()));
        Cable Cable34_6 = new Cable(router5, router9, (int) (distribucionNormal()));
        Cable Cable34_7 = new Cable(router5, router10, (int) (distribucionNormal()));
        Cable Cable34_8 = new Cable(router5, router11, (int) (distribucionNormal()));
        Cable Cable34_9 = new Cable(router6, router8, (int) (distribucionNormal()));
        Cable Cable34_10 = new Cable(router6, router9, (int) (distribucionNormal()));
        Cable Cable34_11 = new Cable(router6, router10, (int) (distribucionNormal()));
        Cable Cable34_12 = new Cable(router6, router11, (int) (distribucionNormal()));
        Cable Cable34_13 = new Cable(router7, router8, (int) (distribucionNormal()));
        Cable Cable34_14 = new Cable(router7, router9, (int) (distribucionNormal()));
        Cable Cable34_15 = new Cable(router7, router10, (int) (distribucionNormal()));
        Cable Cable34_16 = new Cable(router7, router11, (int) (distribucionNormal()));

        // Cables entre Conjunto 4 y Conjunto 5
        Cable Cable45_1 = new Cable(router8, router12, (int) (distribucionNormal()));
        Cable Cable45_2 = new Cable(router8, router13, (int) (distribucionNormal()));
        Cable Cable45_3 = new Cable(router9, router12, (int) (distribucionNormal()));
        Cable Cable45_4 = new Cable(router9, router13, (int) (distribucionNormal()));
        Cable Cable45_5 = new Cable(router10, router12, (int) (distribucionNormal()));
        Cable Cable45_6 = new Cable(router10, router13, (int) (distribucionNormal()));
        Cable Cable45_7 = new Cable(router11, router12, (int) (distribucionNormal()));
        Cable Cable45_8 = new Cable(router11, router13, (int) (distribucionNormal()));

        // Cables entre Conjunto 5 y Conjunto 6
        Cable Cable56_1 = new Cable(router12, router14, (int) (distribucionNormal()));
        Cable Cable56_2 = new Cable(router13, router14, (int) (distribucionNormal()));

        grafo.agregarCable(Cable01_1);
        grafo.agregarCable(Cable01_2);
        grafo.agregarCable(Cable01_3);
        grafo.agregarCable(Cable23_1);
        grafo.agregarCable(Cable23_2);
        grafo.agregarCable(Cable23_3);
        grafo.agregarCable(Cable23_4);
        grafo.agregarCable(Cable23_5);
        grafo.agregarCable(Cable23_6);
        grafo.agregarCable(Cable23_7);
        grafo.agregarCable(Cable23_8);
        grafo.agregarCable(Cable23_9);
        grafo.agregarCable(Cable23_10);
        grafo.agregarCable(Cable23_11);
        grafo.agregarCable(Cable23_12);
        grafo.agregarCable(Cable34_1);
        grafo.agregarCable(Cable34_2);
        grafo.agregarCable(Cable34_3);
        grafo.agregarCable(Cable34_4);
        grafo.agregarCable(Cable34_5);
        grafo.agregarCable(Cable34_6);
        grafo.agregarCable(Cable34_7);
        grafo.agregarCable(Cable34_8);
        grafo.agregarCable(Cable34_9);
        grafo.agregarCable(Cable34_10);
        grafo.agregarCable(Cable34_11);
        grafo.agregarCable(Cable34_12);
        grafo.agregarCable(Cable34_13);
        grafo.agregarCable(Cable34_14);
        grafo.agregarCable(Cable34_15);
        grafo.agregarCable(Cable34_16);
        grafo.agregarCable(Cable45_1);
        grafo.agregarCable(Cable45_2);
        grafo.agregarCable(Cable45_3);
        grafo.agregarCable(Cable45_4);
        grafo.agregarCable(Cable45_5);
        grafo.agregarCable(Cable45_6);
        grafo.agregarCable(Cable45_7);
        grafo.agregarCable(Cable45_8);
        grafo.agregarCable(Cable56_1);
        grafo.agregarCable(Cable56_2);
        
        consola.setVisible(true);
        simulacion.setGrafo2D(grafo);

        buscar(
                grafo, 
                router0, 
                router14
        );
    }
    
    public void actualizar() {
        for(Cable aristas: grafo.getCables()) {
            aristas.setPing((int) distribucionNormal());
        }
    }

    public static ArrayList<String> leer() {
        ArrayList<String> paquetes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(
                        "../enrutamiento/src/modelo/archivo.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                paquetes.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paquetes;
    }
    
    public void guardar(ArrayList<String> paquetes, String nombreArchivo) {
        try {
            File file = new File(nombreArchivo);
            FileWriter fileWriter = new FileWriter(file);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (String str : paquetes) {
                    bufferedWriter.write(str);
                    bufferedWriter.newLine(); // Agregar un salto de línea después de cada string
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void buscar(Red grafo, Router nodo_A, Router nodo_B) throws IOException, InterruptedException {
        ArrayList<Cable> caminoSolucion;
        ArrayList<String> paquetesAEnviar = leer();
        ArrayList<String> paquetesRecibidos = new ArrayList<>();
        Boolean redCaida = false;
        
        while (!paquetesAEnviar.isEmpty()) {
            caminoSolucion = busqueda.obtenerRuta(grafo, nodo_A, nodo_B);
            simulacion.setTransmision(caminoSolucion);
            enrutar();
            
            caminoSolucion.get(caminoSolucion.size() - 1).getNodei().setPaquete(paquetesAEnviar.get(0));
            Router nodoActual = caminoSolucion.get(caminoSolucion.size() - 1).getNodei();
            
            for (int i = caminoSolucion.size() - 1; i >= 0; i--) {
                caminoSolucion.get(i).getNodef().setPaquete(nodoActual.getPaquete());
                nodoActual = caminoSolucion.get(i).getNodef();
                
                if (caminoSolucion.get(i).getPing() > 0) {
                    Thread.sleep(caminoSolucion.get(i).getPing() * RETARDO_INTERNET);

                    if (i != 0 && caminoSolucion.get(i - 1).getNodef() == nodoActual) {
                        caminoSolucion.get(i - 1).getNodei().setPaquete(nodoActual.getPaquete());
                        nodoActual = caminoSolucion.get(i - 1).getNodei();

                    } else if (i != 0 && caminoSolucion.get(i - 1).getNodef() != nodoActual) {
                        caminoSolucion.get(i - 1).getNodef().setPaquete(nodoActual.getPaquete());
                        nodoActual = caminoSolucion.get(i - 1).getNodef();
                    }
                    consola("Mensaje de " + caminoSolucion.get(i).getNodei().getNombre() + " a "
                            + caminoSolucion.get(i).getNodef().getNombre() + ": "
                            + caminoSolucion.get(i).getNodef().getPaquete() + "\n");
                } else {
                    intentosMaximos++;
                    if(intentosMaximos == 10) {
                        JOptionPane.showMessageDialog(null, "Envío fallido. Red inestable");
                        System.exit(0); 
                    }
                    
                    consola("La red presenta fallos. Intentando reestablecer conexión\n");
                    actualizar();
                    buscar(grafo, nodo_A, nodo_B);
                    
                    break;
                }
            }
            paquetesRecibidos.add(paquetesAEnviar.get(0));
            paquetesAEnviar.remove(paquetesAEnviar.get(0));
            actualizar();
        }
        // FIN CODIGO DE ENVÍO DE MENSAJES
        guardar(paquetesRecibidos, "Paquetes de llegada.txt"); 
        JOptionPane.showMessageDialog(null, "Envío satisfactorio");
        System.exit(0);
    }

    private void enrutar() {
        SwingUtilities.invokeLater(() -> {            
            int delay = 500;
            Timer timer = new Timer(delay, (ActionEvent e) -> {
                for (java.awt.Component component : simulacion.getContentPane().getComponents()) {
                    simulacion.getContentPane().remove(component);
                }
                
                simulacion.setGrafo2D(grafo);
                simulacion.graficar();
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
    
    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }
    
    private void consola(String texto) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                consola.addText(texto);
            }
        });
    }
    
    private double distribucionNormal() {
        double mediaAritmetica = 0.4;
        double desviacionEstandar = 0.25;
        double variableAleatoria;
        
        variableAleatoria = desviacionEstandar * rand.nextGaussian() + mediaAritmetica;
        
        return variableAleatoria * 100;
    }

    private String generarDireccionesIP() {
    StringBuilder direccionIP = new StringBuilder();
    for (int j = 0; j < 4; j++) {
        direccionIP.append(rand.nextInt(256)); // Genera un número aleatorio entre 0 y 255
        if (j < 3) {
            direccionIP.append(".");
        }
    }
    return direccionIP.toString();
}
}
