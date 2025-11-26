/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

public class FileManager implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private DiscoDuro disco;
    private GestorAsignacion gestorAsignacion;
    private ProcessManager processManager;
    private DiscoManager planificadorActual; 
    
    private NodeDirectory root; 
    private int currentHeadPosition; 

    public FileManager() {
        this.disco = new DiscoDuro();
        this.currentHeadPosition = 0; 

        this.gestorAsignacion = new GestorAsignacion(this.disco);
        this.processManager = new ProcessManager();
        
        this.planificadorActual = new FIFOManager();
        
        this.root = new NodeDirectory("root", null);
    }


    
    public NodeDirectory getRoot() {
        return root;
    }
    
    public DiscoDuro getDisco() {
        return disco;
    }
    
    public ProcessManager getProcessManager() {
        return processManager;
    }

    public GestorAsignacion getGestorAsignacion() {
        return gestorAsignacion;
    }

    public DiscoManager getPlanificador() {
        return planificadorActual;
    }

    public int getCurrentHeadPosition() {
        return currentHeadPosition;
    }

    public void setCurrentHeadPosition(int currentHeadPosition) {
        this.currentHeadPosition = currentHeadPosition;
    }
}