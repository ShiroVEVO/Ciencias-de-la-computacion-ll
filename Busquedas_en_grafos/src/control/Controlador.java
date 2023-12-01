package control;

import control.busqueda.Busqueda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;
import vista.interfaz.Mapa_colombia;
import vista.interfaz.SelectorMunicipio;

public class Controlador implements ActionListener {
    private final Busqueda a_Asterisco;
    private final Grafo grafo;
    private final Mapa_colombia mapa;
    private final SelectorMunicipio selector;

    public Controlador(Busqueda a_Asterisco, Grafo grafo, Mapa_colombia mapa, SelectorMunicipio selector) {
        this.a_Asterisco = a_Asterisco;
        this.grafo = grafo;
        this.mapa = mapa;
        this.selector = selector;
        
        selector.btnIniciar.addActionListener(this);
    }

    public void iniciar() throws IOException {
        Nodo nodo0 = new Nodo(0, 0, "Bogotá", 0);
        Nodo nodo1 = new Nodo(1, 1, "Tunja", 123);
        Nodo nodo2 = new Nodo(2, 2, "Manizales", 164);
        Nodo nodo3 = new Nodo(3, 3, "Medellin", 242);
        Nodo nodo4 = new Nodo(4, 4, "Barrancabermeja", 264);
        Nodo nodo5 = new Nodo(5, 5, "Bucaramanga", 292);
        Nodo nodo6 = new Nodo(6, 6, "Cúcuta", 399);
        Nodo nodo7 = new Nodo(7, 7, "Ibague", 132);
        Nodo nodo8 = new Nodo(8, 8, "Pereira", 180);
        Nodo nodo9 = new Nodo(9, 9, "Montería", 490);
        Nodo nodo10 = new Nodo(10, 10, "Sincelejo", 532);
        Nodo nodo11 = new Nodo(11, 11, "Cartagena", 642);
        Nodo nodo12 = new Nodo(12, 12, "Barranquilla", 696);
        Nodo nodo13 = new Nodo(13, 13, "Santa Marta", 723);
        Nodo nodo14 = new Nodo(14, 14, "Valledupar", 643);
        Nodo nodo15 = new Nodo(15, 15, "Bosconia", 577);
        Nodo nodo16 = new Nodo(16, 16, "Riohacha", 763);
        Nodo nodo17 = new Nodo(17, 17, "Cali", 306);
        Nodo nodo18 = new Nodo(18, 18, "Villavicencio", 78);
        Nodo nodo19 = new Nodo(19, 19, "Neiva", 241);

        grafo.agregarNodo(nodo0);
        grafo.agregarNodo(nodo1);
        grafo.agregarNodo(nodo2);
        grafo.agregarNodo(nodo3);
        grafo.agregarNodo(nodo4);
        grafo.agregarNodo(nodo5);
        grafo.agregarNodo(nodo6);
        grafo.agregarNodo(nodo7);
        grafo.agregarNodo(nodo8);
        grafo.agregarNodo(nodo9);
        grafo.agregarNodo(nodo10);
        grafo.agregarNodo(nodo11);
        grafo.agregarNodo(nodo12);
        grafo.agregarNodo(nodo13);
        grafo.agregarNodo(nodo14);
        grafo.agregarNodo(nodo15);
        grafo.agregarNodo(nodo16);
        grafo.agregarNodo(nodo17);
        grafo.agregarNodo(nodo18);
        grafo.agregarNodo(nodo19);
        
        DefaultComboBoxModel<String> modelo1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelo2 = new DefaultComboBoxModel<>();
        for(int i = 0; i < grafo.getNodos().size(); i++) {
            modelo1.addElement(grafo.getNodos().get(i).getNombre());
            modelo2.addElement(grafo.getNodos().get(i).getNombre());
        }
        selector.cbMunicipio1.setModel(modelo1);
        selector.cbMunicipio2.setModel(modelo2);

        Arista arista0 = new Arista(nodo0, nodo2, 291);
        Arista arista1 = new Arista(nodo0, nodo7, 209);
        Arista arista2 = new Arista(nodo0, nodo18, 125);
        Arista arista3 = new Arista(nodo1, nodo0, 139);
        Arista arista4 = new Arista(nodo2, nodo8, 54);
        Arista arista5 = new Arista(nodo3, nodo1, 412);
        Arista arista6 = new Arista(nodo3, nodo2, 196);
        Arista arista7 = new Arista(nodo3, nodo4, 301);
        Arista arista8 = new Arista(nodo5, nodo4, 115);
        Arista arista9 = new Arista(nodo6, nodo5, 197);
        Arista arista10 = new Arista(nodo8, nodo7, 120);
        Arista arista11 = new Arista(nodo9, nodo3, 403);
        Arista arista12 = new Arista(nodo9, nodo4, 548);
        Arista arista13 = new Arista(nodo10, nodo6, 699);
        Arista arista14 = new Arista(nodo10, nodo9, 118);
        Arista arista15 = new Arista(nodo10, nodo15, 246);
        Arista arista16 = new Arista(nodo11, nodo10, 191);
        Arista arista17 = new Arista(nodo11, nodo15, 329);
        Arista arista18 = new Arista(nodo12, nodo11, 125);
        Arista arista19 = new Arista(nodo12, nodo15, 207);
        Arista arista20 = new Arista(nodo13, nodo12, 103);
        Arista arista21 = new Arista(nodo13, nodo15, 161);
        Arista arista22 = new Arista(nodo14, nodo15, 96);
        Arista arista23 = new Arista(nodo16, nodo13, 172);
        Arista arista24 = new Arista(nodo16, nodo14, 160);
        Arista arista25 = new Arista(nodo17, nodo7, 267);
        Arista arista26 = new Arista(nodo17, nodo8, 209);
        Arista arista27 = new Arista(nodo18, nodo19, 417);
        Arista arista28 = new Arista(nodo19, nodo17, 382);

        grafo.agregarArista(arista0);
        grafo.agregarArista(arista1);
        grafo.agregarArista(arista2);
        grafo.agregarArista(arista3);
        grafo.agregarArista(arista4);
        grafo.agregarArista(arista5);
        grafo.agregarArista(arista6);
        grafo.agregarArista(arista7);
        grafo.agregarArista(arista8);
        grafo.agregarArista(arista9);
        grafo.agregarArista(arista10);
        grafo.agregarArista(arista11);
        grafo.agregarArista(arista12);
        grafo.agregarArista(arista13);
        grafo.agregarArista(arista14);
        grafo.agregarArista(arista15);
        grafo.agregarArista(arista16);
        grafo.agregarArista(arista17);
        grafo.agregarArista(arista18);
        grafo.agregarArista(arista19);
        grafo.agregarArista(arista20);
        grafo.agregarArista(arista21);
        grafo.agregarArista(arista22);
        grafo.agregarArista(arista23);
        grafo.agregarArista(arista24);
        grafo.agregarArista(arista25);
        grafo.agregarArista(arista26);
        grafo.agregarArista(arista27);
        grafo.agregarArista(arista28);

        selector.setVisible(true);
    }
    
    private void buscar(Grafo grafo, Nodo nodo_A, Nodo nodo_B) throws IOException {
        ArrayList<Arista> caminoSolucion;
        
        caminoSolucion = a_Asterisco.obtenerRuta(grafo, nodo_A, nodo_B);
        if(caminoSolucion != null) {
            mapa.pintarSolucion(caminoSolucion);
            mapa.setVisible(true);
        } else {
            buscar(grafo, nodo_B, nodo_A);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selector.btnIniciar) {
            int indice_nodo1 = selector.cbMunicipio1.getSelectedIndex();
            int indice_nodo2 = selector.cbMunicipio2.getSelectedIndex();
            
            if(indice_nodo1 == indice_nodo2) { JOptionPane.showMessageDialog(null, "Verifique el municipio o ciudad al que se quiere dirigir."); }
            else{
                selector.dispose();
                try {
                    buscar(
                        grafo,
                        grafo.getNodos().get(indice_nodo1),
                        grafo.getNodos().get(indice_nodo2)
                    );
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
