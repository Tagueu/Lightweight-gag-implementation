package fr.inria.gag.specification;

import java.util.ArrayList;

public class DecompositionRule {

	private String name;
	private Guard guard;
	private ArrayList<Service> subServices;
	private SemanticRule semantic;
	
	
	public DecompositionRule() {
		subServices= new ArrayList<Service>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Guard getGuard() {
		return guard;
	}
	public void setGuard(Guard guard) {
		this.guard = guard;
	}
	public ArrayList<Service> getSubServices() {
		return subServices;
	}
	public void setSubServices(ArrayList<Service> subServices) {
		this.subServices = subServices;
	}
	public SemanticRule getSemantic() {
		return semantic;
	}
	public void setSemantic(SemanticRule semantic) {
		this.semantic = semantic;
	}
	
	
	
}
