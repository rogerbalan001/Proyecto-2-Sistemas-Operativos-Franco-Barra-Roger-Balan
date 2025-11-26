/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author frank
 */
public class HiloSimulador extends Thread {
    
    private FileManager fileManager;
    private GUI gui; 
    
    private boolean corriendo = true;
    private boolean pausado = false;

    public HiloSimulador(FileManager fileManager, GUI gui) {
        this.fileManager = fileManager;
        this.gui = gui;
    }

    @Override
    public void run() {
        System.out.println("--- KERNEL (SIMULADOR) INICIADO ---");
        
        while (corriendo) {
            try {
                Thread.sleep(1000); 
                
                if (pausado) continue;

                DiscoManager planificador = fileManager.getPlanificador();
                
                if (planificador.hasPendingRequests()) {
                    
                    int headPos = fileManager.getCurrentHeadPosition();
                    IORequests solicitud = planificador.getNextRequest(headPos);
                    
                    if (solicitud != null) {
                        procesarSolicitud(solicitud);
                    }
                }
                
            } catch (InterruptedException e) {
                System.err.println("Hilo Simulador interrumpido: " + e.getMessage());
            }
        }
    }
    
    private void procesarSolicitud(IORequests req) {
        System.out.println(">>> PROCESANDO: " + req.getType() + " en bloque obj: " + req.getTargetBlock());

        proyecto2francobarrarogerbalan.Process proc = req.getOwnerProcess();
        fileManager.getProcessManager().setProcessState(proc, EstadoProceso.EJECUTANDO);
        
        actualizarGUI(); 
        
        fileManager.setCurrentHeadPosition(req.getTargetBlock());
        
        boolean exito = true;
        
        try {
            Thread.sleep(500); 

            switch (req.getType()) {
                case CREAR:
                    if (req.getArchivoPendiente() instanceof NodeFile) {
                        NodeFile archivo = (NodeFile) req.getArchivoPendiente();
                        
                        exito = fileManager.getGestorAsignacion().allocateFile(archivo);
                        
                        if (!exito) {
                            System.err.println("FALLO: Disco lleno al intentar crear archivo.");
                            if (archivo.getParent() != null) {
                                archivo.getParent().getChildren().remove(archivo);
                            }
                        }
                    }
                    break;
                    
                case ELIMINAR:
                    if (req.getArchivoPendiente() instanceof NodeFile) {
                        NodeFile archivo = (NodeFile) req.getArchivoPendiente();
                        fileManager.getGestorAsignacion().deallocateFile(archivo);
                        
                        if (archivo.getParent() != null) {
                            archivo.getParent().getChildren().remove(archivo);
                        }
                    } 
                    else if (req.getArchivoPendiente() instanceof NodeDirectory) {
                        NodeDirectory dir = (NodeDirectory) req.getArchivoPendiente();
                        if (dir.getParent() != null) {
                            dir.getParent().getChildren().remove(dir);
                        }
                    }
                    break;
                    
                case LEER:
                case ESCRIBIR:
                    break;
            }
        } catch (InterruptedException e) {
            exito = false;
        }

        fileManager.getProcessManager().terminateProcess(proc);
        
        boolean finalExito = exito;
        SwingUtilities.invokeLater(() -> {
            
            gui.actualizarVistas(); 
            
            gui.refrescarArbol(); 
            
            if (!finalExito) {
                JOptionPane.showMessageDialog(gui, 
                        "Error Crítico: No hay espacio suficiente en el disco.", 
                        "Error de E/S", 
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void actualizarGUI() {
        SwingUtilities.invokeLater(() -> gui.actualizarVistas());
    }
    
    public void detener() {
        this.corriendo = false;
    }
    
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
}