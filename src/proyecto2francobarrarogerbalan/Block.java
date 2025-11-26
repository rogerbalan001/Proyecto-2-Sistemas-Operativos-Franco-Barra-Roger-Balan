/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; 

public class Block implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int nextBlock; 
    
    private boolean isFree;

    public Block() {
        this.isFree = true;
        this.nextBlock = -1;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }
    
    public void freeBlock() {
        this.isFree = true;
        this.nextBlock = -1;
    }
}