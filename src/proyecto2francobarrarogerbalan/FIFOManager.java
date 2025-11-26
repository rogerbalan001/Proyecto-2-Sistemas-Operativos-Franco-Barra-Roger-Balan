/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; // <--- IMPORTANTE

/**
 * @author frank
 */
public class FIFOManager implements DiscoManager, Serializable { 
    
    private static final long serialVersionUID = 1L; 
    
    private Cola<IORequests> requestQueue;

    public FIFOManager() {
        this.requestQueue = new Cola<>();
    }

    @Override
    public void addRequest(IORequests request) {
        this.requestQueue.enqueue(request); 
    }

    @Override
    public IORequests getNextRequest(int currentHeadPosition) {
        if (!hasPendingRequests()) {
            return null;
        }
        return this.requestQueue.dequeue();
    }

    @Override
    public boolean hasPendingRequests() {
        return !this.requestQueue.isEmpty();
    }
}