package fr.inria.gag.configuration;

import java.util.ArrayList;

import fr.inria.gag.specification.RuntimeData;

public class Configuration extends RuntimeData{
    private Task root;
    private ArrayList<PendingLocalFunctionComputation> pendingLocalComputations;
    
    public Configuration() {
    	pendingLocalComputations = new ArrayList<PendingLocalFunctionComputation>();
    }
	public Task getRoot() {
		return root;
	}
	public void setRoot(Task root) {
		this.root = root;
	}
	public ArrayList<PendingLocalFunctionComputation> getPendingLocalComputations() {
		return pendingLocalComputations;
	}
	public void setPendingLocalComputations(ArrayList<PendingLocalFunctionComputation> pendingLocalComputations) {
		this.pendingLocalComputations = pendingLocalComputations;
	}
    
    
}
