package fr.inria.gag.configuration;

import java.util.ArrayList;

import fr.inria.gag.specification.Service;

public class Task {
	private String AppliedRule;
	private boolean open=true;
	private Service service;
	private ArrayList<Data> inputs;
	private ArrayList<Data> outputs;
	private ArrayList<Task> subTasks;
	
	public Task() {
		inputs = new ArrayList<Data>();
		outputs = new ArrayList<Data>();
		subTasks = new ArrayList<Task>();
	}
	public String getAppliedRule() {
		return AppliedRule;
	}
	public void setAppliedRule(String appliedRule) {
		AppliedRule = appliedRule;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public ArrayList<Data> getInputs() {
		return inputs;
	}
	public void setInputs(ArrayList<Data> inputs) {
		this.inputs = inputs;
	}
	public ArrayList<Data> getOutputs() {
		return outputs;
	}
	public void setOutputs(ArrayList<Data> outputs) {
		this.outputs = outputs;
	}
	public ArrayList<Task> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(ArrayList<Task> subTask) {
		this.subTasks = subTask;
	} 
	
	
}