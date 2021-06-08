package fr.inria.gag.specification;

import java.util.ArrayList;

public class Service {
      
	private String name;
	private Boolean axiom;
	private ArrayList<Parameter> inputParameters;
	private ArrayList<Parameter> outputParameters;
	private ArrayList<DecompositionRule> rules;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isAxiom() {
		return axiom;
	}
	public void setAxiom(Boolean axiom) {
		this.axiom = axiom;
	}
	public ArrayList<Parameter> getInputParameters() {
		return inputParameters;
	}
	public void setInputParameters(ArrayList<Parameter> inputParameters) {
		this.inputParameters = inputParameters;
	}
	public ArrayList<Parameter> getOutputParameters() {
		return outputParameters;
	}
	public void setOutputParameters(ArrayList<Parameter> outputParameters) {
		this.outputParameters = outputParameters;
	}
	public ArrayList<DecompositionRule> getRules() {
		return rules;
	}
	public void setRules(ArrayList<DecompositionRule> rules) {
		this.rules = rules;
	}
	
	
}