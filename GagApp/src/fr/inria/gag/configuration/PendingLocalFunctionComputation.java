package fr.inria.gag.configuration;

import java.util.ArrayList;

import fr.inria.gag.specification.FunctionDeclaration;

public class PendingLocalFunctionComputation {
	private Data dataToCompute;
	private ArrayList<Data> actualParameters;
	private FunctionDeclaration functionDeclaration;

	public Data getDataToCompute() {
		return dataToCompute;
	}

	public void setDataToCompute(Data dataToCompute) {
		this.dataToCompute = dataToCompute;
	}

	public ArrayList<Data> getActualParameters() {
		return actualParameters;
	}

	public void setActualParameters(ArrayList<Data> actualParameters) {
		this.actualParameters = actualParameters;
	}

	public FunctionDeclaration getFunctionDeclaration() {
		return functionDeclaration;
	}

	public void setFunctionDeclaration(FunctionDeclaration functionDeclaration) {
		this.functionDeclaration = functionDeclaration;
	}

}
