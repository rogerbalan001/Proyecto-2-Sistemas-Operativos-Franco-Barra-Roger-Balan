/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

/**
 *
 * @author frank
 */
import java.io.Serializable; 

public class Process implements Serializable { 
    
    private static final long serialVersionUID = 1L;
    
    private static int nextPID = 1; 
    private int pid;
    private String name;
    private EstadoProceso state;

    public Process(String name) {
        this.pid = nextPID++;
        this.name = (name == null || name.isEmpty()) ? "Process-" + this.pid : name;
        this.state = EstadoProceso.NUEVO;
    }


    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public EstadoProceso getState() {
        return state;
    }

    public void setState(EstadoProceso state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PID: " + pid + " (" + name + ") - " + state;
    }
}
