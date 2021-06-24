package cm.uds.fuchsia.gag.model.specification;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;

public class Service {
      
	private String name;
	private Boolean axiom;
	private ArrayList<Parameter> inputParameters;
	private ArrayList<Parameter> outputParameters;
	private ArrayList<DecompositionRule> rules;
	// fields that apply when the service is remote
	
	private Boolean remote=false; // default false
	private String location;
	
	public Service() {
		rules = new ArrayList<DecompositionRule>();
		inputParameters= new ArrayList<Parameter>();
		outputParameters= new ArrayList<Parameter>();
		axiom=false;
	}
	@XmlAttribute @XmlID
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute
	public Boolean isAxiom() {
		return axiom;
	}
	public void setAxiom(Boolean axiom) {
		this.axiom = axiom;
	}
	@XmlElement(name="input")
	public ArrayList<Parameter> getInputParameters() {
		return inputParameters;
	}
	public void setInputParameters(ArrayList<Parameter> inputParameters) {
		this.inputParameters = inputParameters;
	}
	@XmlElement(name="output")
	public ArrayList<Parameter> getOutputParameters() {
		return outputParameters;
	}
	public void setOutputParameters(ArrayList<Parameter> outputParameters) {
		this.outputParameters = outputParameters;
	}
	@XmlElement(name="production")
	public ArrayList<DecompositionRule> getRules() {
		return rules;
	}
	public void setRules(ArrayList<DecompositionRule> rules) {
		this.rules = rules;
	}
	@XmlAttribute
	public Boolean isRemote() {
		return remote;
	}
	public void setRemote(Boolean remote) {
		this.remote = remote;
	}
	@XmlAttribute
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
}