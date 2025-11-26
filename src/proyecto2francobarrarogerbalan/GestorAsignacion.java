/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; 

public class GestorAsignacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private DiscoDuro disco;

    public GestorAsignacion(DiscoDuro disco) {
        this.disco = disco;
    }

    public boolean allocateFile(NodeFile file) {
        int blocksNeeded = file.getSizeInBlocks();

        if (blocksNeeded > disco.getFreeBlocksCount()) {
            System.err.println("Error de asignación: No hay suficientes bloques libres.");
            return false;
        }

        if (blocksNeeded == 0) {
            file.setFirstBlock(0);
            return true;
        }

        int firstBlock = disco.findFreeBlock();
        if (firstBlock == -1) {
            return false; 
        }
        
        file.setFirstBlock(firstBlock);
        
        int prevBlockIndex = firstBlock;
        int currentBlockIndex;

        for (int i = 1; i < blocksNeeded; i++) {
            currentBlockIndex = disco.findFreeBlock();
            
            disco.allocateBlock(prevBlockIndex, currentBlockIndex);
            
            prevBlockIndex = currentBlockIndex;
        }

        disco.allocateBlock(prevBlockIndex, 0); 
        
        return true;
    }

    public void deallocateFile(NodeFile file) {
        int firstBlock = file.getFirstBlock();
        
        if (firstBlock <= 0) { 
            return; 
        }

        disco.freeChainedBlocks(firstBlock);
        
        file.setFirstBlock(-1);
    }
}