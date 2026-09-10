/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3_parcial2_programacion2;

/**
 *
 * @author denam
 */
import javax.swing.*;
import java.util.*;
public class descarga implements Runnable {
    
    private String nombre;
    private JProgressBar barra;
    private Random random = new Random();
    private programaDescargas programa;
    public descarga(String nombre, JProgressBar barra, programaDescargas programa) {
        this.nombre = nombre;
        this.barra = barra;
        this.programa= programa;
    }
    
    
    public void run() {
        int progreso = 0;
         boolean paso50=true;
        boolean paso20=true;
        boolean paso90=true;
        while (progreso < 100 && !programa.cancelado) {
            try {
                
                
                int incremento = random.nextInt(10) + 1;
                int pausa = random.nextInt(400) + 100;

                progreso += incremento;
                if (progreso > 100) {
                    progreso = 100;
                }

                int progresoFinal = progreso;

                SwingUtilities.invokeLater(() -> {
                    barra.setValue(progresoFinal);
                });
                if (progreso>15 && progreso <30&&paso20){
                    programa.agregarMensaje(nombre + ": descarga al " + 20 + "%");
                    paso20=false;
                }else if (progreso>45 && progreso <60&& paso50){
                    programa.agregarMensaje(nombre + ": descarga al " + 50 + "%");
                    paso50=false;
                }else if (progreso>89 && progreso <100&&paso90){
                    programa.agregarMensaje(nombre + ": descarga al " + 99 + "%");
                    paso90=false;
                }
                    
                
                

                Thread.sleep(pausa);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                programa.agregarMensaje(nombre + ": hilo interrumpido.");
                return;
            }
        }
        if (programa.cancelado) {
               programa. agregarMensaje(nombre + ": descarga cancelada.");
            } else {
                programa.agregarMensaje(nombre + ": descarga completada.");
                programa.marcarComoCompletada();
            }

        
        
       
        
    }
    
}
