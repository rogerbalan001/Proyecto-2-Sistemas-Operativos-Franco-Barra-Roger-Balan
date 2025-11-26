/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

/**
 * @author frank
 */
public class DiscoDuro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final int TOTAL_BLOCKS = 256; 
    
    private Block[] blocks;
    private int freeBlocksCount;

    public DiscoDuro() {
        this.blocks = new Block[TOTAL_BLOCKS];
        this.freeBlocksCount = TOTAL_BLOCKS;
        
        for (int i = 0; i < TOTAL_BLOCKS; i++) {
            this.blocks[i] = new Block();
        }
    }

    public synchronized int findFreeBlock() { 
        if (freeBlocksCount == 0) {
            return -1; 
        }
        
        for (int i = 0; i < TOTAL_BLOCKS; i++) {
            if (blocks[i].isFree()) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void allocateBlock(int index, int nextBlock) { 
        if (index >= 0 && index < TOTAL_BLOCKS) {
            if (blocks[index].isFree()) {
                this.freeBlocksCount--; 
            }
            blocks[index].setFree(false);
            blocks[index].setNextBlock(nextBlock);
        }
    }

    public synchronized void freeBlock(int blockIndex) { 
        if (blockIndex < 0 || blockIndex >= TOTAL_BLOCKS) {
            return; 
        }
        
        if (!blocks[blockIndex].isFree()) {
            blocks[blockIndex].setFree(true); 
            blocks[blockIndex].setNextBlock(-1); 
            this.freeBlocksCount++;
        }
    }
    
    public synchronized void freeChainedBlocks(int firstBlockIndex) { 
        int currentIndex = firstBlockIndex;
        
        while (currentIndex != -1 && currentIndex != 0) { 
            if (currentIndex >= TOTAL_BLOCKS) break; 
            
            int nextIndex = blocks[currentIndex].getNextBlock();
            
            if (!blocks[currentIndex].isFree()) {
                 blocks[currentIndex].setFree(true);
                 blocks[currentIndex].setNextBlock(-1);
                 this.freeBlocksCount++;
            }
            
            currentIndex = nextIndex;
        }
    }

    public int getFreeBlocksCount() {
        return freeBlocksCount;
    }

    public int getTotalBlocks() {
        return TOTAL_BLOCKS;
    }
    
    public Block[] getBlocks() {
        return blocks;
    }
}