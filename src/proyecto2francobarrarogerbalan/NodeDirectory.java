/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

/**
 *
 * @author frank
 */
public class NodeDirectory extends Node {
    
    private List<Node> children; 

    public NodeDirectory(String name, NodeDirectory parent) {
        super(name, parent);
        this.children = new List<>();
    }

    public void addChild(Node child) {
        child.setParent(this); 
        this.children.add(child);
    }
    
    public Node findChild(String name) {
        NodeList<Node> current = children.getHead();
        while (current != null) {
            if (current.getData().getName().equals(name)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null; 
    }

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public int getSizeInBlocks() {
        return 1; 
    }
}
