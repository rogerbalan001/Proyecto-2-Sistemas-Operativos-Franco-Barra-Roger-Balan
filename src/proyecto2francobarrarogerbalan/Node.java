/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; 

/**
 * @author frank
 */
public abstract class Node implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected NodeDirectory parent;

    public Node(String name, NodeDirectory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeDirectory getParent() {
        return parent;
    }

    public void setParent(NodeDirectory parent) {
        this.parent = parent;
    }

    public String getPath() {
        if (parent == null) { 
            return "/"; 
        }
        String parentPath = parent.getPath();
        if (parentPath.equals("/")) {
            return "/" + name;
        }
        return parent.getPath() + "/" + name;
    }

    public abstract int getSizeInBlocks();

    @Override
    public String toString() {
        return name; 
    }
}