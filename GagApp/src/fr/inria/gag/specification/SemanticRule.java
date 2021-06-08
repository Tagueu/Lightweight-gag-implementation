package fr.inria.gag.specification;

import java.util.ArrayList;

public class SemanticRule {

	private ArrayList<FunctionDeclaration> functionDeclarations;
	private ArrayList<Equation> equations;
	
	public ArrayList<FunctionDeclaration> getFunctionDeclarations() {
		return functionDeclarations;
	}
	public void setFunctionDeclarations(ArrayList<FunctionDeclaration> functionDeclarations) {
		this.functionDeclarations = functionDeclarations;
	}
	public ArrayList<Equation> getEquations() {
		return equations;
	}
	public void setEquations(ArrayList<Equation> equations) {
		this.equations = equations;
	}
	
	
}
