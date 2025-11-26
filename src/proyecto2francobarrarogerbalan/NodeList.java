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

public class NodeList<T> implements Serializable { 
    
    private static final long serialVersionUID = 1L;
    
    private T data; 
    private NodeList<T> next; 

    public NodeList(T data) {
        this.data = data;
        this.next = null;
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeList<T> getNext() {
        return next;
    }

    public void setNext(NodeList<T> next) {
        this.next = next;
    }
}
