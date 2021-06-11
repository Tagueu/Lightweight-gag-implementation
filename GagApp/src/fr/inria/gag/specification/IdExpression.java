package fr.inria.gag.specification;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



public class IdExpression extends Expression{
	private String serviceName;
	private String parameterName;
	
	@XmlAttribute(name="service")
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@XmlAttribute(name="parameter")
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	
}
