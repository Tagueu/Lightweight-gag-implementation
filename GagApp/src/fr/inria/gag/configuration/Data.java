package fr.inria.gag.configuration;

import fr.inria.gag.specification.Parameter;

public class Data {
	private Object value;
	private Parameter parameter;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
