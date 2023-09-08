/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.estrategias;

import java.util.ArrayList;
import java.util.Arrays;
import modelo.Producto;

/**
 *
 * @author amirz
 */
public class BurstSort implements Ordenamiento {

    private int columnaOrdenar;
    
    @Override
    public ArrayList<Producto> ordenar(ArrayList<Producto> listaProductos, int opcion) {
        setColumnaOrdenar(opcion);
        return burstSort(listaProductos);
    }
    
    private ArrayList<Producto> burstSort(ArrayList<Producto> listaProductos) {
        if(listaProductos.size() == 1) { return listaProductos; }
        
        ArrayList<ArrayList<Producto>> sublistas = dividir(listaProductos);
        
        ArrayList<Producto> lista1 = burstSort(sublistas.get(0));
        ArrayList<Producto> lista2 = burstSort(sublistas.get(1));
        
        return unir(lista1, lista2);
    }
    
    private static ArrayList<ArrayList<Producto>> dividir(ArrayList<Producto> listaProductos) {
        ArrayList<ArrayList<Producto>> listaSublistas;
  
        listaSublistas = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>()));
        
        int mitad = listaProductos.size() / 2;
        
        for(int i = 0; i < mitad; i++) {
            listaSublistas.get(0).add(listaProductos.get(i));
        }
        
        for(int i = mitad; i < listaProductos.size(); i++) {
            listaSublistas.get(1).add(listaProductos.get(i));
        }
        
        return listaSublistas;
    }
    
    private ArrayList<Producto> unir(ArrayList<Producto> lista1, ArrayList<Producto> lista2) {
        ArrayList<Producto> union = new ArrayList<>();
        int i = 0;
        int j = 0;
        
        while(i < lista1.size() && j < lista2.size()) {  
            if(comparar(lista1, lista2, i, j))  { union.add(lista1.get(i++)); } 
            else                                { union.add(lista2.get(j++)); }
        }
        
        while(i < lista1.size())    { union.add(lista1.get(i++)); }
        while(j < lista2.size())    { union.add(lista2.get(j++)); }
        
        return union;
    }

    private void setColumnaOrdenar(int columnaOrdenar) {
        this.columnaOrdenar = columnaOrdenar;
    }
    
    private boolean comparar(ArrayList<Producto> lista1, ArrayList<Producto> lista2, int i, int j) {
        return switch (columnaOrdenar) {
            case 1 -> lista1.get(i).getId() < lista2.get(j).getId();
            case 2 -> (lista1.get(i).getNombre().compareTo(lista2.get(j).getNombre())) < 0;
            case 3 -> (lista1.get(i).getDescripcion().compareTo(lista2.get(j).getDescripcion())) < 0;
            case 4 -> lista1.get(i).getPrecio() < lista2.get(j).getPrecio();
            default -> false;
        };
    }
}
