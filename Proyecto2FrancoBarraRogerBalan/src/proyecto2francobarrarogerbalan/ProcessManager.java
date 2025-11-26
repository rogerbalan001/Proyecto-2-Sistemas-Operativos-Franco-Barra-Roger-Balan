/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

/**
 * Administrador de la Tabla de Procesos (PCB).
 * @author frank
 */
public class ProcessManager implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Usamos nuestra lista personalizada
    private List<Process> processTable;

    public ProcessManager() {
        this.processTable = new List<>();
    }
    
    /**
     * Crea un nuevo proceso y lo registra.
     */
    public Process createProcess(String name) {
        Process newProcess = new Process(name);
        this.processTable.add(newProcess);
        return newProcess;
    }
    
    /**
     * Marca un proceso como TERMINADO.
     */
    public void terminateProcess(Process process) {
        if (process != null) {
            process.setState(EstadoProceso.TERMINADO);
        }
    }
    
    /**
     * Cambia el estado de un proceso.
     */
    public void setProcessState(Process process, EstadoProceso newState) {
        if (process != null) {
            process.setState(newState);
        }
    }
    
    /**
     * --- ¡ESTE ES EL MÉTODO QUE TE FALTABA! ---
     * Devuelve la lista completa de procesos para que la GUI pueda pintarlos.
     */
    public List<Process> getProcessTable() {
        return processTable;
    }
}