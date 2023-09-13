/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author amirz
 */
public class Consola extends JFrame {
    private JTextArea datosConsola;
    private JPanel consola;
    private JScrollPane barraDeslizante;
    
    public Consola() {
        setTitle("Consola");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        construir();
    }
    
    private void construir() {
        consola = new JPanel();
        consola.setLayout(new BorderLayout());
        getContentPane().add(consola);
        
        datosConsola = new JTextArea();
        datosConsola.setEditable(false);
  
        barraDeslizante = new JScrollPane(datosConsola);
        consola.add(barraDeslizante);
    }
    
    public void imprimir(String texto) {
        datosConsola.append(texto + "\n");
    }
    
    public void limpiar() {
        datosConsola.setText(null);
    }
}
