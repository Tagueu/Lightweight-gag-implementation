package fr.inria.gag.specification;

import java.util.ArrayList;

public class GAG {

	private String name;
	private RuntimeData configuration;
	private ArrayList<Service> services;
	
	public GAG() {
		services = new ArrayList<Service>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RuntimeData getConfiguration() {
		return configuration;
	}
	public void setConfiguration(RuntimeData configuration) {
		this.configuration = configuration;
	}
	public ArrayList<Service> getServices() {
		return services;
	}
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
	
	
}
