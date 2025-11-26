/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; // <--- IMPORTANTE

/**
 * @author frank
 */
public class SCANManager implements DiscoManager, Serializable { 

    private static final long serialVersionUID = 1L;

    private List<IORequests> requestList;
    private boolean movingTowardsEnd; 

    public SCANManager() {
        this.requestList = new List<>();
        this.movingTowardsEnd = true; 
    }

    @Override
    public void addRequest(IORequests request) {
        this.requestList.add(request);
    }

    @Override
    public boolean hasPendingRequests() {
        return !this.requestList.isEmpty();
    }

    @Override
    public IORequests getNextRequest(int currentHeadPosition) {
        if (!hasPendingRequests()) {
            return null;
        }

        IORequests bestRequest = null;
        int minSeekTime = Integer.MAX_VALUE;

        NodeList<IORequests> current = requestList.getHead();
        
        while (current != null) {
            IORequests req = current.getData();
            int distance = req.getTargetBlock() - currentHeadPosition;

            if (movingTowardsEnd) {
                if (distance >= 0 && distance < minSeekTime) {
                    minSeekTime = distance;
                    bestRequest = req;
                }
            } 
            else {
                if (distance <= 0 && Math.abs(distance) < minSeekTime) {
                    minSeekTime = Math.abs(distance);
                    bestRequest = req;
                }
            }
            current = current.getNext();
        }

        if (bestRequest == null) {
            movingTowardsEnd = !movingTowardsEnd; 
            
            current = requestList.getHead();
            minSeekTime = Integer.MAX_VALUE;
            
            while (current != null) {
                IORequests req = current.getData();
                int distance = req.getTargetBlock() - currentHeadPosition;

                if (movingTowardsEnd) {
                    if (distance >= 0 && distance < minSeekTime) {
                        minSeekTime = distance;
                        bestRequest = req;
                    }
                } else {
                    if (distance <= 0 && Math.abs(distance) < minSeekTime) {
                        minSeekTime = Math.abs(distance);
                        bestRequest = req;
                    }
                }
                current = current.getNext();
            }
        }
        
        if (bestRequest != null) {
            this.requestList.remove(bestRequest);
        }
        return bestRequest;
    }
}