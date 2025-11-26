/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; 
/**
 * @author frank
 * @param <T> 
 */
public class Cola<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private NodeList<T> front; 
    private NodeList<T> rear;  
    private int size;

    public Cola() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        NodeList<T> newNode = new NodeList<>(data);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T data = front.getData();
        
        front = front.getNext();

        if (front == null) {
            rear = null;
        }
        
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}