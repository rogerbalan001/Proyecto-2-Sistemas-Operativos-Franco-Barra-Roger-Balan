/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;    

/**
 * @author frank
 */
public class SSTFManager implements DiscoManager, Serializable { 

    private static final long serialVersionUID = 1L;

    private List<IORequests> requestList;

    public SSTFManager() {
        this.requestList = new List<>();
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

        NodeList<IORequests> current = requestList.getHead();
        
        IORequests bestRequest = null;
        int minSeekTime = Integer.MAX_VALUE;

        while (current != null) {
            IORequests req = current.getData();
            
            int seekTime = Math.abs(req.getTargetBlock() - currentHeadPosition);

            if (seekTime < minSeekTime) {
                minSeekTime = seekTime;
                bestRequest = req;
            }
            current = current.getNext();
        }

        if (bestRequest != null) {
            this.requestList.remove(bestRequest);
        }
        
        return bestRequest;
    }
}