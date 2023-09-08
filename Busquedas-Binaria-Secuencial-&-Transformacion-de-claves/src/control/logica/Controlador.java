/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.logica;

import control.estrategias.BusquedaSecuencial;
import control.estrategias.BusquedaTransformacionClaves;
import control.estrategias.BusquedaBinaria;
import control.estrategias.Busqueda;
import control.estrategias.BurstSort;
import modelo.Producto;
import control.estrategias.TablaHash;
import modelo.TiendaProductos;
import vista.Vista;
import modelo.Archivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import control.estrategias.Ordenamiento;

/**
 *
 * @author amirz
 */
public class Controlador implements ActionListener {

    private static final String[] nombresColumnas = {"ID", "Nombre", "Descripción", "Precio"};
    
    private Vista vista;
    private TiendaProductos modelo;
    
    private Archivo listaProductos;
    
    private ObservadorControlador estadoInicio;
    private ObservadorControlador estadoBusquedaEliminacion;
    
    private Ordenamiento hash; 
    private Ordenamiento burstSort;
    private Busqueda secuencial;
    private Busqueda binaria;
    private Busqueda transformacionClaves;

    public Controlador(Vista vista, TiendaProductos modelo) {
        hash = new TablaHash();
        burstSort = new BurstSort();
        
        secuencial = new BusquedaSecuencial();
        binaria = new BusquedaBinaria();
        transformacionClaves = new BusquedaTransformacionClaves();
        
        estadoInicio = new EstadoVentanaPrincipal();
        estadoBusquedaEliminacion = new EstadoVentanaBusquedaEliminacion();
        
        listaProductos = new Archivo();
        
        this.vista = vista;
        this.modelo = modelo;

        this.vista.ventanaInicio.btnBuscarEliminar.addActionListener(this);
        this.vista.ventanaInicio.btnInsertar.addActionListener(this);
        this.vista.ventanaInicio.btnOrdenamientoBurst.addActionListener(this);
        this.vista.ventanaInicio.btnOrdenamientoHash.addActionListener(this);
        this.vista.ventanaInicio.cbColumnaOrdenar.addActionListener(this);
        
        this.vista.ventanaInsertar.btnInsertar.addActionListener(this);
        
        this.vista.ventanaBusquedaEliminacion.btnBuscar.addActionListener(this);
        this.vista.ventanaBusquedaEliminacion.btnEliminar.addActionListener(this);
        this.vista.ventanaBusquedaEliminacion.btnEliminarTodo.addActionListener(this);
        this.vista.ventanaBusquedaEliminacion.cbBusqueda.addActionListener(this);
        this.vista.ventanaBusquedaEliminacion.cbColumnaBuscar.addActionListener(this);
    }
    
    public void iniciar() {
        cargarArchivo();
        vista.ventanaInicio.setLocationRelativeTo(null);
        
        vista.ventanaInsertar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        vista.ventanaBusquedaEliminacion.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
        vista.ventanaInicio.tablaProductos.setModel(cargarTablaBusqueda(modelo.getProductosAplicacion()));
        vista.ventanaInicio.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.ventanaInicio.cbColumnaOrdenar) { cbColumnaOrdenar(); }
        if(e.getSource() == vista.ventanaInicio.btnOrdenamientoBurst) { btnOrdenamientoBurst(); }
        if(e.getSource() == vista.ventanaInicio.btnOrdenamientoHash) { btnOrdenamientoHash(); }
        if(e.getSource() == vista.ventanaInicio.btnInsertar) { vista.ventanaInsertar.setVisible(true); }
        if(e.getSource() == vista.ventanaInicio.btnBuscarEliminar) { vista.ventanaBusquedaEliminacion.setVisible(true); }
        
        if(e.getSource() == vista.ventanaBusquedaEliminacion.cbBusqueda) { cbBusqueda(); }
        if(e.getSource() == vista.ventanaBusquedaEliminacion.cbColumnaBuscar) { cbColumnaBuscar(); }
        if(e.getSource() == vista.ventanaBusquedaEliminacion.btnBuscar) { btnBuscar(); }
        if(e.getSource() == vista.ventanaBusquedaEliminacion.btnEliminar) { btnEliminar(); }
        if(e.getSource() == vista.ventanaBusquedaEliminacion.btnEliminarTodo) { btnEliminarTodo(); }
        
        if(e.getSource() == vista.ventanaInsertar.btnInsertar) { btnInsertar(); }
    }

    private void cbColumnaOrdenar() {
        String columnaSeleccionada = (String) vista.ventanaInicio.cbColumnaOrdenar.getSelectedItem();      
        estadoInicio.notificarCambio("Columna", columnaSeleccionada);
    }
    
    
    private void cbBusqueda() {
        String itemSeleccionado = (String) vista.ventanaBusquedaEliminacion.cbBusqueda.getSelectedItem();
        
        estadoBusquedaEliminacion.notificarCambio("Busqueda", itemSeleccionado);
        cambiarEstrategiaBusqueda(estadoBusquedaEliminacion.obtenerEstado("Busqueda"));
    }
    
    
    private void cbColumnaBuscar() {
        String columnaSeleccionada = (String) vista.ventanaBusquedaEliminacion.cbColumnaBuscar.getSelectedItem();
        estadoBusquedaEliminacion.notificarCambio("Columna", columnaSeleccionada);
    }
    
    
    private void btnOrdenamientoBurst() {
        if(estadoInicio.obtenerEstado("Columna") == -1) {
            vista.mensajeError("No se ha seleccionado ordenamiento");
        } else {
            cambiarEstrategiaOrdenamiento(1);
            modelo.ordenarProductos(estadoInicio.obtenerEstado("Columna"));
            estadoInicio.notificarCambio("Ordenamiento", "BurstSort");
            
            vista.ventanaInicio.tablaProductos.setModel(cargarTablaBusqueda(
                modelo.getProductosAplicacion()
            ));
        }
    }
    
    
    private void btnOrdenamientoHash() {
        switch (estadoInicio.obtenerEstado("Columna")) {
            case -1 -> vista.mensajeError("No se ha seleccionado ordenamiento");
            case 3 -> vista.mensajeError("No existe soporte de Hashing para la columna seleccionada.");
            default -> {
                cambiarEstrategiaOrdenamiento(2);
                modelo.ordenarProductos(estadoInicio.obtenerEstado("Columna"));
                estadoInicio.notificarCambio("Ordenamiento", "Tabla Hash");
                
                vista.ventanaInicio.tablaProductos.setModel(cargarTablaBusqueda(
                    modelo.getProductosAplicacion()
                ));
            }
        }
    }
    
    
    private void btnInsertar() {
        try {
            int precio = Integer.parseInt(vista.ventanaInsertar.campoPrecio.getText());
            String nombre = vista.ventanaInsertar.campoNombre.getText();
            String descripcion = vista.ventanaInsertar.campoDescripcion.getText();
            
            boolean camposVacios = nombre.isBlank() || descripcion.isBlank();
                
            if(camposVacios) { vista.mensajeError("Algún campo está vacío."); }
            else 
            { 
                modelo.insertarProducto(new Producto(nombre, descripcion, precio));          
                vista.ventanaInsertar.dispose();
                limpiarVentanaInsertar();
            
                vista.ventanaInicio.tablaProductos.setModel(cargarTablaBusqueda(
                    modelo.getProductosAplicacion()
                ));
            }
        } catch (NumberFormatException nfe) {
            vista.mensajeError("El precio ingresado es inválido.");
        }
    }
    
    
    private void btnBuscar() {
        boolean seleccionesVacias = 
                estadoBusquedaEliminacion.obtenerEstado("Busqueda") == -1 || 
                estadoBusquedaEliminacion.obtenerEstado("Columna") == -1;
        
        boolean validarBinaria = 
                estadoInicio.obtenerEstado("Ordenamiento") == 1 && 
                (
                        estadoInicio.obtenerEstado("Columna") ==
                        estadoBusquedaEliminacion.obtenerEstado("Columna")
                );
        
        boolean validarTransformacionClaves = 
                estadoInicio.obtenerEstado("Ordenamiento") == 2 && 
                (
                        estadoInicio.obtenerEstado("Columna") ==
                        estadoBusquedaEliminacion.obtenerEstado("Columna")
                );
        
        boolean busquedaSinSoporte = 
                estadoBusquedaEliminacion.obtenerEstado("Columna") == 3 &&
                (
                    estadoBusquedaEliminacion.obtenerEstado("Busqueda") == 2 ||
                    estadoBusquedaEliminacion.obtenerEstado("Busqueda") == 3
                );
        
        boolean esBinaria = estadoBusquedaEliminacion.obtenerEstado("Busqueda") == 2;
        boolean esTransformacionClaves = estadoBusquedaEliminacion.obtenerEstado("Busqueda") == 3;
        boolean valorEnBlanco = vista.ventanaBusquedaEliminacion.tfValor.getText().isBlank();
     
        if(seleccionesVacias)                   { vista.mensajeError("No se ha seleccionado búsqueda o columna"); }    
        else if(valorEnBlanco)                  { vista.mensajeError("No se ha digitado valor a buscar"); }
        else if(busquedaSinSoporte)             { vista.mensajeError("La búsqueda por descripción es sólo soportada por la búsqueda secuencial"); }
        else if(esBinaria && !validarBinaria)   { vista.mensajeError("Para usar esta búsqueda, ordene primero con BurstSort la columna a buscar."); }
        
        else if(esTransformacionClaves && !validarTransformacionClaves) 
        { vista.mensajeError("Para usar esta búsqueda, ordene primero con Tabla Hash la columna a buscar."); }
        
        else 
        {
            long tiempoInicial = System.nanoTime();
            vista.ventanaBusquedaEliminacion.tablaBusqueda.setModel(
                cargarTablaBusqueda(modelo.buscarProductos
                    (
                    estadoBusquedaEliminacion.obtenerEstado("Columna"), 
                    vista.ventanaBusquedaEliminacion.tfValor.getText()
                    )
                )
            );
            long tiempoFinal = System.nanoTime();
            long duracion = (tiempoFinal - tiempoInicial);
            System.out.println(duracion);
            
            this.vista.ventanaBusquedaEliminacion.tfTiempo.setText(Long.toString(duracion) + " ns");
            if(vista.ventanaBusquedaEliminacion.tablaBusqueda.getModel().getRowCount() == 0) 
            { vista.mensajeInformacion("No se encontró ningún valor en la lista de productos.");  }
        }
    }
    
    
    private void btnEliminar() {
        int filaSeleccionada = vista.ventanaBusquedaEliminacion.tablaBusqueda.getSelectedRow();
        int auxBusqueda = estadoBusquedaEliminacion.obtenerEstado("Busqueda");
       
        String claveSeleccionada;
        DefaultTableModel auxiliar;
        
        modelo.setEstrategiaBusqueda(secuencial);
        
        claveSeleccionada = Integer.toString((int) vista.ventanaBusquedaEliminacion.tablaBusqueda.getValueAt(
             filaSeleccionada, 
             0
        ));
            
        modelo.eliminarProducto(claveSeleccionada);
        auxiliar = (DefaultTableModel) vista.ventanaBusquedaEliminacion.tablaBusqueda.getModel();
        auxiliar.removeRow(filaSeleccionada);

        vista.ventanaBusquedaEliminacion.tablaBusqueda.setModel(auxiliar);
        vista.ventanaInicio.tablaProductos.setModel(
            cargarTablaBusqueda(modelo.getProductosAplicacion())
        );
        
        cambiarEstrategiaBusqueda(auxBusqueda);
    }
    
    private void btnEliminarTodo() {
        int auxBusqueda = estadoBusquedaEliminacion.obtenerEstado("Busqueda");
        
        modelo.setEstrategiaBusqueda(secuencial);
        
        DefaultTableModel auxiliar; 
        auxiliar = (DefaultTableModel) vista.ventanaBusquedaEliminacion.tablaBusqueda.getModel();
        
        while(auxiliar.getRowCount() > 0) { auxiliar.removeRow(0); }
        
        modelo.eliminarProductos(
            estadoBusquedaEliminacion.obtenerEstado("Columna"), 
            (String) vista.ventanaBusquedaEliminacion.tfValor.getText()
        );
            
        vista.ventanaBusquedaEliminacion.tablaBusqueda.setModel(auxiliar);
        vista.ventanaInicio.tablaProductos.setModel(
            cargarTablaBusqueda(modelo.getProductosAplicacion())
        );
        
        cambiarEstrategiaBusqueda(auxBusqueda);
    }
    
    private DefaultTableModel cargarTablaBusqueda(ArrayList<Producto> listaProductos) {
        DefaultTableModel modeloTabla = new DefaultTableModel(nombresColumnas, 0);
        
        for(Producto producto: listaProductos) {
                    modeloTabla.addRow(new Object[]{
                        producto.getId(), 
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio()
                    });
        }
        
        return modeloTabla;
    }
    
    private void limpiarVentanaInsertar() {
        vista.ventanaInsertar.campoDescripcion.setText("");
        vista.ventanaInsertar.campoNombre.setText("");
        vista.ventanaInsertar.campoPrecio.setText("");
    }
    
    private void cambiarEstrategiaBusqueda(int columnaOrden) {
        switch(columnaOrden) {
            case 1 -> modelo.setEstrategiaBusqueda(secuencial);
            case 2 -> modelo.setEstrategiaBusqueda(binaria);
            case 3 -> modelo.setEstrategiaBusqueda(transformacionClaves);
            default -> modelo.setEstrategiaBusqueda(null);
        }
    }
    
    private void cambiarEstrategiaOrdenamiento(int opcion) {
        switch(opcion) {
            case 1 -> modelo.setEstrategiaOrdenamiento(burstSort);
            case 2 -> modelo.setEstrategiaOrdenamiento(hash);
            default -> modelo.setEstrategiaOrdenamiento(null);
        }
    }
    
    private void cargarArchivo() {
        modelo.setProductosAplicacion(listaProductos.cargarArchivos());
    }
}
