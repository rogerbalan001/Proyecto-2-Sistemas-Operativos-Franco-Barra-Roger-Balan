/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

/**
 * * @author frank
 * @param <T> 
 */
public class List<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private NodeList<T> head; 
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        NodeList<T> newNode = new NodeList<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            NodeList<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public boolean remove(T data) {
        if (isEmpty()) {
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return true;
        }

        NodeList<T> current = head;
        NodeList<T> prev = null;

        while (current != null && !current.getData().equals(data)) {
            prev = current;
            current = current.getNext();
        }

        if (current != null) {
            prev.setNext(current.getNext());
            size--;
            return true;
        }

        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
    
    public NodeList<T> getHead() {
        return head;
    }
}