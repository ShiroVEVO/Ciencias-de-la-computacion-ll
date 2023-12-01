/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 *
 * @author amirz
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {
    
    public ArrayList<Producto> cargarArchivos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto auxiliar;
        int i = 0;

        
        String archivoCSV = "../Busquedas-Ciencias-II/datos_ficticios.csv";
        
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV))) {
            String lineaTexto;
            boolean primerLinea = true;  // Para omitir la primera línea (encabezados)
            

            while ((lineaTexto = lector.readLine()) != null && i < 15000) {

                if (primerLinea) {
                    primerLinea = false;
                    continue;  // Omitir la primera línea (encabezados)
                }
                
                String[] parts = lineaTexto.split(",");
                if (parts.length >= 3) {
                    String producto = parts[0];
                    String descripcion = parts[1];
                    int precio = Integer.parseInt(parts[2]);
                    auxiliar = new Producto(producto, descripcion, precio);
                    listaProductos.add(auxiliar);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return listaProductos;
    }
}
