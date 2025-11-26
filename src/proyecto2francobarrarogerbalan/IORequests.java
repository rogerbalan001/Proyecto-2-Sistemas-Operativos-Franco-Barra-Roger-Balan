/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

/**
 *
 * @author frank
 */
public class IORequests implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Process ownerProcess; 
    private TipoSolicitud type;   
    
    private String filePath;      
    private int sizeInBlocks;     
    
    private int targetBlock;      
    
    private Node archivoPendiente; 

    public IORequests(Process ownerProcess, TipoSolicitud type, String filePath, int sizeInBlocks, int targetBlock) {
        this.ownerProcess = ownerProcess;
        this.type = type;
        this.filePath = filePath;
        this.sizeInBlocks = sizeInBlocks;
        this.targetBlock = targetBlock;
        this.archivoPendiente = null; 
        
        if (this.ownerProcess != null) {
            this.ownerProcess.setState(EstadoProceso.BLOQUEADO);
        }
    }

    public Process getOwnerProcess() {
        return ownerProcess;
    }

    public void setOwnerProcess(Process ownerProcess) {
        this.ownerProcess = ownerProcess;
    }

    public TipoSolicitud getType() {
        return type;
    }

    public void setType(TipoSolicitud type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getSizeInBlocks() {
        return sizeInBlocks;
    }

    public void setSizeInBlocks(int sizeInBlocks) {
        this.sizeInBlocks = sizeInBlocks;
    }

    public int getTargetBlock() {
        return targetBlock;
    }

    public void setTargetBlock(int targetBlock) {
        this.targetBlock = targetBlock;
    }

    public Node getArchivoPendiente() {
        return archivoPendiente;
    }

    public void setArchivoPendiente(Node archivoPendiente) {
        this.archivoPendiente = archivoPendiente;
    }
    
    @Override
    public String toString() {
        return type + " -> " + filePath + " [Bloque: " + targetBlock + "]";
    }
}