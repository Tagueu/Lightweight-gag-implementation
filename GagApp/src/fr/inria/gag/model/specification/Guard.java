package fr.inria.gag.model.specification;

import javax.xml.bind.annotation.XmlAttribute;

public class Guard {
	private String location;
	private String method;

	@XmlAttribute
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@XmlAttribute
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
