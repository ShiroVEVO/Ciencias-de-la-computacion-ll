/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelo.Cable;
import modelo.Red;
import modelo.Router;

/**
 *
 * @author amirz
 */
public class RedComunicacion extends JFrame {
    private BufferedImage redFondo;
    private BufferedImage routerRed;
    private Red grafo2D;
    private ArrayList<Cable> transmision;
    private ArrayList<Line2D> cableadoRed;
    
    private int numeroPantalla = -1;
    private int aristaActual;
    
    private static final int CENTRO_IMAGEN_ROUTER = 24;
    
    public RedComunicacion() {
        setTitle("Simulación de enrutamiento");
        setSize(1366, 768);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(0,0);
    }
    
    public void graficar() {
        cargarElementos();
        
        cableadoRed = generarLineas();
        graficarElementos();
        setVisible(true);
    }
    
    private void cargarElementos() {
        try {
            String rutaRelativaFondo = "../enrutamiento/src/vista/imagenes/red_fondo_pantalla.png";
            String rutaRelativaRouter = "../enrutamiento/src/vista/imagenes/router.png";
            
            redFondo = ImageIO.read(new File(rutaRelativaFondo));
            routerRed = ImageIO.read(new File(rutaRelativaRouter));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void graficarElementos() {
        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2D = (Graphics2D) g;
                
                g.drawImage(redFondo, 0, 0, getWidth(), getHeight(), this);
                
                g2D.setStroke(new BasicStroke(3.5f));
                for(Cable cable: grafo2D.getCables())  {
                    if(cable.getPing() < 0 && transmision.contains(cable))      { g2D.setColor(Color.RED); }
                    else if(cable.getPing() < 0)                                  { g2D.setColor(Color.ORANGE);}
                    else if(transmision.contains(cable))                        { g2D.setColor(Color.CYAN); } 
                    else                                                          { g2D.setColor(Color.BLUE); }
                    g2D.draw(cableadoRed.get(cable.getId()));
                }
        
                if(numeroPantalla >= 0) {
                    int xString = (int) ((cableadoRed.get(aristaActual).getX1() + cableadoRed.get(aristaActual).getX2())/2);
                    int yString = (int) ((cableadoRed.get(aristaActual).getY1() + cableadoRed.get(aristaActual).getY2())/2);
            
                    g2D.setColor(Color.GREEN);
                    g2D.setFont(new Font("default", Font.BOLD, 18));
                                        
                    g2D.drawString(
                        Integer.toString(grafo2D.getCables().get(aristaActual).getPing()),
                        xString,
                        yString
                    );
                }
                
                for(Router nodo: grafo2D.getNodos()) {
                    g.drawImage(
                            routerRed, 
                            nodo.getX() - CENTRO_IMAGEN_ROUTER, 
                            nodo.getY() - CENTRO_IMAGEN_ROUTER,
                            this
                    );
                }
            }
           
            {
                //Evento de visualización de pings de cables de transmisión
                addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int mouseX = e.getX();
                        int mouseY = e.getY();
                
                        for(int i = 0; i < cableadoRed.size(); i++) {
                            if(isMouseOverLine(cableadoRed.get(i), mouseX, mouseY)) {
                                numeroPantalla = grafo2D.getCables().get(i).getPing();
                                aristaActual = i;
                                break;
                            }
                        }
                        repaint();
                    }
                });
            }
        };        
        setContentPane(grafico);
    }
    
    private ArrayList<Line2D> generarLineas() {
        ArrayList<Line2D> lineas = new ArrayList<>();
        
        for(Cable cable: grafo2D.getCables()) {
            lineas.add(new Line2D.Double(
                cable.getNodei().getX(), 
                cable.getNodei().getY(),
                cable.getNodef().getX(), 
                cable.getNodef().getY()
            ));
        }
        
        return lineas;
    }
    
    public void setGrafo2D(Red grafo2D) {
        this.grafo2D = grafo2D;
    }
    
    public Red getGrafo2D() {
        return this.grafo2D;
    }
    
    public void setTransmision(ArrayList<Cable> transmision) {
        this.transmision = transmision;
    }
    
    public boolean isMouseOverLine(Line2D linea, int x, int y) {
        return linea.ptSegDist(x, y) < 5;
    }
}
