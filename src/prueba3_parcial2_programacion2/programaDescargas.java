/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba3_parcial2_programacion2;

/**
 *
 * @author denam
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class programaDescargas extends JFrame {
    private int Descargas =0;
    
    public volatile boolean cancelado = false;
    private JProgressBar barra1, barra2,barra3;
    private JButton iniciar, finalizar;
    private JTextArea salida;
    private JPanel Medio;
    
    
    public programaDescargas(){
        super("Descargador");
        setSize(600, 400);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        initBarras();
        initBotones();
        initArea();
        
        
        
        
        
        
        
        
    }
    
    private void initBarras(){
         JPanel panelBarras = new JPanel(new GridLayout(3, 1, 10, 10));
         Medio = new JPanel();
         Medio.setLayout(new BoxLayout(Medio, BoxLayout.X_AXIS));

        
        barra1= new JProgressBar(0, 100);
        barra1.setValue(0);
        barra1.setStringPainted(true);
        
        barra2= new JProgressBar(0, 100);
        barra2.setValue(0);
        barra2.setStringPainted(true);
        
        barra3= new JProgressBar(0, 100);
        barra3.setValue(0);
        barra3.setStringPainted(true);
        
        panelBarras.add(PonerTituloenPanel("Archivo 1", barra1));
        panelBarras.add(PonerTituloenPanel("Archivo 2", barra2));
        panelBarras.add(PonerTituloenPanel("Archivo 3", barra3));
        Medio.add(panelBarras);
        add(panelBarras, BorderLayout.CENTER);
    }
    
    private JPanel PonerTituloenPanel(String nombre, JProgressBar barra){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(nombre), BorderLayout.NORTH);
        panel.add(barra, BorderLayout.CENTER);
        return panel;
    }
    private void initBotones(){
        JPanel Botones = new JPanel();
        iniciar = new JButton("Iniciar");
        finalizar = new JButton("Cancelar");
        iniciar.addActionListener (ev ->{
        iniciarDescargas();
        });
        
        finalizar.addActionListener (ev ->{
        cancelarDescargas();
        });

        Botones.add(iniciar);
        Botones.add(finalizar);
        add(Botones, BorderLayout.SOUTH);
    }
    private void initArea(){
         salida = new JTextArea(30,30);
        salida.setEditable(false);
        salida.setText(" ");
        JScrollPane scroll = new JScrollPane(salida);
        scroll.setMinimumSize(new Dimension (500, 1000));
        scroll.setMaximumSize(new Dimension (500, 1000));
        Medio.add(scroll);
        add(Medio,BorderLayout.WEST );
    }
    
    private void iniciarDescargas(){
        cancelado = false;
        Descargas =0;
        barra1.setValue(0);
        barra2.setValue(0);
        barra3.setValue(0);
       

        Thread t1 = new Thread(new descarga("Archivo 1", barra1, this));
        Thread t2 = new Thread(new descarga("Archivo 2", barra2, this));// agregar objetos de descarga
        Thread t3 = new Thread(new descarga("Archivo 3", barra3, this));

        t1.start();
        t2.start();
        t3.start();

        agregarMensaje("Se iniciaron las 3 descargas.");
    }
    public void agregarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> {
           salida.append(mensaje  + "\n");
        });
    }
    private void cancelarDescargas() {
        cancelado = true;
        agregarMensaje("Se solicitó cancelar todas las descargas.");
    }
    
    
    
    
     public synchronized void marcarComoCompletada() {
        Descargas++;
        if (Descargas == 3) {
            agregarMensaje("Todas las descargas han finalizado.");
        }
    }
    
    
    
}
