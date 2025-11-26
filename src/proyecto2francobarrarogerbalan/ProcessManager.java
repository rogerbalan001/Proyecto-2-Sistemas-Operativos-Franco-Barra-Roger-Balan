/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

/**
 * @author frank
 */
public class ProcessManager implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<Process> processTable;

    public ProcessManager() {
        this.processTable = new List<>();
    }
    
    public Process createProcess(String name) {
        Process newProcess = new Process(name);
        this.processTable.add(newProcess);
        return newProcess;
    }
    
    public void terminateProcess(Process process) {
        if (process != null) {
            process.setState(EstadoProceso.TERMINADO);
        }
    }
    
    public void setProcessState(Process process, EstadoProceso newState) {
        if (process != null) {
            process.setState(newState);
        }
    }
    
    public List<Process> getProcessTable() {
        return processTable;
    }
}