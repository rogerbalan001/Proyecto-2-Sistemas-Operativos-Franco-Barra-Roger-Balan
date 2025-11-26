/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

/**
 * Representación del Hardware (Disco Físico).
 * Gestiona los bloques brutos de memoria.
 * @author frank
 */
public class DiscoDuro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Definimos un tamaño máximo de bloques
    public static final int TOTAL_BLOCKS = 256; 
    
    private Block[] blocks;
    private int freeBlocksCount;

    public DiscoDuro() {
        this.blocks = new Block[TOTAL_BLOCKS];
        this.freeBlocksCount = TOTAL_BLOCKS;
        
        // Inicializar todos los bloques como libres
        for (int i = 0; i < TOTAL_BLOCKS; i++) {
            this.blocks[i] = new Block();
        }
    }

    /**
     * Busca y devuelve el índice del primer bloque libre que encuentra.
     * SYNCHRONIZED: Garantiza exclusión mutua durante la búsqueda.
     * @return El índice del bloque libre, o -1 si el disco está lleno.
     */
    public synchronized int findFreeBlock() { // <--- AGREGADO SYNCHRONIZED
        if (freeBlocksCount == 0) {
            return -1; // No hay espacio
        }
        
        for (int i = 0; i < TOTAL_BLOCKS; i++) {
            if (blocks[i].isFree()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Asigna un bloque específico y lo enlaza con el siguiente.
     * SYNCHRONIZED: Evita que dos procesos ocupen el mismo bloque a la vez.
     * @param index Índice del bloque a ocupar.
     * @param nextBlock Índice del siguiente bloque en la cadena (o 0 para EOF).
     */
    public synchronized void allocateBlock(int index, int nextBlock) { // <--- AGREGADO SYNCHRONIZED
        if (index >= 0 && index < TOTAL_BLOCKS) {
            if (blocks[index].isFree()) {
                this.freeBlocksCount--; // Solo restamos si estaba libre
            }
            blocks[index].setFree(false);
            blocks[index].setNextBlock(nextBlock);
        }
    }

    /**
     * Libera un bloque específico.
     * SYNCHRONIZED: Protege el contador de bloques libres.
     * @param blockIndex Índice del bloque a liberar.
     */
    public synchronized void freeBlock(int blockIndex) { // <--- AGREGADO SYNCHRONIZED
        if (blockIndex < 0 || blockIndex >= TOTAL_BLOCKS) {
            return; // Índice fuera de rango
        }
        
        if (!blocks[blockIndex].isFree()) {
            blocks[blockIndex].setFree(true); // Usar el setter correcto
            blocks[blockIndex].setNextBlock(-1); // Resetear puntero
            this.freeBlocksCount++;
        }
    }
    
    /**
     * Libera una cadena completa de bloques (asignación encadenada).
     * @param firstBlockIndex El índice del primer bloque del archivo.
     */
    public synchronized void freeChainedBlocks(int firstBlockIndex) { // <--- AGREGADO SYNCHRONIZED
        int currentIndex = firstBlockIndex;
        
        while (currentIndex != -1 && currentIndex != 0) { // 0 es EOF
            if (currentIndex >= TOTAL_BLOCKS) break; // Seguridad
            
            int nextIndex = blocks[currentIndex].getNextBlock();
            
            // Llamamos a la versión interna o copiamos lógica para evitar deadlock, 
            // pero como es reentrante en Java, podemos llamar a freeBlock.
            // Sin embargo, para ser puristas, hacemos la lógica aquí:
            if (!blocks[currentIndex].isFree()) {
                 blocks[currentIndex].setFree(true);
                 blocks[currentIndex].setNextBlock(-1);
                 this.freeBlocksCount++;
            }
            
            currentIndex = nextIndex;
        }
    }

    // --- Getters ---

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