/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import modelo.Arista;

public class Mapa_colombia extends JFrame{
    private JLabel mapa;
    
    private ArrayList<Arista> caminoSolucion;
    
    private static int RADIO_NODO = 20;
    private static int AJUSTE_X = 11;
    private static int AJUSTE_Y = 12;
            
    private static final int[][] coordenadas = {
        {394, 673}, //0 Bogotá
        {454, 604}, //1 Tunja
        {275, 645}, //2 Manizales
        {268, 544}, //3 Medellín
        {412, 476}, //4 Barrancabermeja
        {474, 472}, //5 Bucaramanga
        {527, 407}, //6 Cúcuta
        {297, 697}, //7 Ibagué
        {257, 670}, //8 Pereira
        {243, 332}, //9 Montería
        {284, 287}, //10    Sincelejo
        {276, 191}, //11    Cartagena de Indias
        {333, 141}, //12    Barranquilla
        {382, 120}, //13    Santa Marta
        {464, 186}, //14    Valledupar
        {409, 230}, //15    Bosconia
        {491, 95}, //16 Riohacha
        {189, 780}, //17    Cali
        {433, 722}, //18    Villavicencio
        {292, 825}, //19    Neiva
    };
    
    private static final Line2D[] aristas = {
        new Line2D.Double(394+AJUSTE_X, 673+AJUSTE_Y, 275+AJUSTE_X, 645+AJUSTE_Y), 
        new Line2D.Double(394+AJUSTE_X, 673+AJUSTE_Y, 297+AJUSTE_X, 697+AJUSTE_Y),
        new Line2D.Double(394+AJUSTE_X, 673+AJUSTE_Y, 433+AJUSTE_X, 722+AJUSTE_Y),
        new Line2D.Double(454+AJUSTE_X, 604+AJUSTE_Y, 394+AJUSTE_X, 673+AJUSTE_Y),
        new Line2D.Double(275+AJUSTE_X, 645+AJUSTE_Y, 257+AJUSTE_X, 670+AJUSTE_Y),
        new Line2D.Double(268+AJUSTE_X, 544+AJUSTE_Y, 454+AJUSTE_X, 604+AJUSTE_Y),
        new Line2D.Double(268+AJUSTE_X, 544+AJUSTE_Y, 275+AJUSTE_X, 645+AJUSTE_Y),
        new Line2D.Double(268+AJUSTE_X, 544+AJUSTE_Y, 412+AJUSTE_X, 476+AJUSTE_Y),
        new Line2D.Double(474+AJUSTE_X, 472+AJUSTE_Y, 412+AJUSTE_X, 476+AJUSTE_Y),
        new Line2D.Double(527+AJUSTE_X, 407+AJUSTE_Y, 474+AJUSTE_X, 472+AJUSTE_Y),
        new Line2D.Double(257+AJUSTE_X, 670+AJUSTE_Y, 297+AJUSTE_X, 697+AJUSTE_Y),
        new Line2D.Double(243+AJUSTE_X, 332+AJUSTE_Y, 268+AJUSTE_X, 544+AJUSTE_Y),
        new Line2D.Double(243+AJUSTE_X, 332+AJUSTE_Y, 412+AJUSTE_X, 476+AJUSTE_Y),
        new Line2D.Double(284+AJUSTE_X, 287+AJUSTE_Y, 527+AJUSTE_X, 407+AJUSTE_Y),
        new Line2D.Double(284+AJUSTE_X, 287+AJUSTE_Y, 243+AJUSTE_X, 332+AJUSTE_Y),
        new Line2D.Double(284+AJUSTE_X, 287+AJUSTE_Y, 409+AJUSTE_X, 230+AJUSTE_Y),
        new Line2D.Double(276+AJUSTE_X, 191+AJUSTE_Y, 284+AJUSTE_X, 287+AJUSTE_Y),
        new Line2D.Double(276+AJUSTE_X, 191+AJUSTE_Y, 409+AJUSTE_X, 230+AJUSTE_Y),
        new Line2D.Double(333+AJUSTE_X, 141+AJUSTE_Y, 276+AJUSTE_X, 191+AJUSTE_Y),
        new Line2D.Double(333+AJUSTE_X, 141+AJUSTE_Y, 409+AJUSTE_X, 230+AJUSTE_Y),
        new Line2D.Double(382+AJUSTE_X, 120+AJUSTE_Y, 333+AJUSTE_X, 141+AJUSTE_Y),
        new Line2D.Double(382+AJUSTE_X, 120+AJUSTE_Y, 409+AJUSTE_X, 230+AJUSTE_Y),
        new Line2D.Double(464+AJUSTE_X, 186+AJUSTE_Y, 409+AJUSTE_X, 230+AJUSTE_Y),
        new Line2D.Double(491+AJUSTE_X, 95+AJUSTE_Y, 382+AJUSTE_X, 120+AJUSTE_Y),
        new Line2D.Double(491+AJUSTE_X, 95+AJUSTE_Y, 464+AJUSTE_X, 186+AJUSTE_Y),
        new Line2D.Double(189+AJUSTE_X, 780+AJUSTE_Y, 297+AJUSTE_X, 697+AJUSTE_Y),
        new Line2D.Double(189+AJUSTE_X, 780+AJUSTE_Y, 257+AJUSTE_X, 670+AJUSTE_Y),
        new Line2D.Double(433+AJUSTE_X, 722+AJUSTE_Y, 292+AJUSTE_X, 825+AJUSTE_Y),
        new Line2D.Double(292+AJUSTE_X, 825+AJUSTE_Y, 189+AJUSTE_X, 780+AJUSTE_Y),
    };
    
    private boolean[] tipoLinea;
    
    public Mapa_colombia() throws IOException {
        tipoLinea = new boolean[29];
        
        setTitle("Mapa de Colombia");
        setSize(626, 980);
        setResizable(false);
        getContentPane().setBackground(Color.cyan);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        construir();
    }
    
    private void construir() throws IOException {
        
        BufferedImage archivoMapa;
        archivoMapa = ImageIO.read(this.getClass().getResource("/vista/imagenes/Mapa_colombia.png"));
        
        construirGrafo(archivoMapa);
    }
    
    private void construirGrafo(BufferedImage archivoPNG) {
        float grosor = 2.0f;
        
        mapa = new JLabel(new ImageIcon(archivoPNG)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2D = (Graphics2D) g;

                for(int i = 0; i < caminoSolucion.size(); i++) {
                    g2D.setStroke(new BasicStroke(grosor));
                    g2D.setColor(Color.CYAN);
                    g2D.draw(aristas[
                            caminoSolucion.get(i).getId()
                    ]);
                    tipoLinea[caminoSolucion.get(i).getId()] = true;
                }
                
                for(int i = 0; i < aristas.length; i++) {
                    if(!tipoLinea[i]) {
                        g2D.setStroke(new BasicStroke(grosor));
                        g2D.setColor(Color.BLUE);
                        g2D.draw(aristas[i]);
                    }
                }
                
                for(int i = 0; i < 20; i++) {
                    GradientPaint grafoGradiente = new GradientPaint(
                        coordenadas[i][0],
                        coordenadas[i][1],
                        Color.BLUE, 
                        coordenadas[i][0] + RADIO_NODO, 
                        coordenadas[i][1] + RADIO_NODO, 
                         Color.BLACK
                    );
                    g2D.setPaint(grafoGradiente);
                    g2D.fill(new Ellipse2D.Double(
                        coordenadas[i][0],
                        coordenadas[i][1],
                        RADIO_NODO,
                        RADIO_NODO
                    ));
                }
            }
        };
        getContentPane().add(mapa);
    }
    
    public void pintarSolucion(ArrayList<Arista> caminoSolucion) {
        this.caminoSolucion = caminoSolucion;
    }
}