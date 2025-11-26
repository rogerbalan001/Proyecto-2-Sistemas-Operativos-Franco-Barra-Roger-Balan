package proyecto2francobarrarogerbalan;


/**
 *
 * @author frank
 */
import java.io.Serializable;
public class NodeFile extends Node implements Serializable { 
    
    private static final long serialVersionUID = 1L;
    
    private int sizeInBlocks; 
    private int firstBlock;   

    public NodeFile(String name, NodeDirectory parent, int sizeInBlocks) {
        super(name, parent);
        this.sizeInBlocks = sizeInBlocks;
        this.firstBlock = -1; 
    }

    @Override
    public int getSizeInBlocks() {
        return this.sizeInBlocks;
    }

    public int getFirstBlock() {
        return firstBlock;
    }

    public void setFirstBlock(int firstBlock) {
        this.firstBlock = firstBlock;
    }
}