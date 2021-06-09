package fr.inria.gag.specification;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;

public class FunctionExpression extends Expression {
	
	private FunctionDeclaration function;
	private ArrayList<IdExpression> idExpressions;
	
	public FunctionExpression() {
		idExpressions= new ArrayList<IdExpression>();
	}
	@XmlAttribute @XmlIDREF
	public FunctionDeclaration getFunction() {
		return function;
	}
	public void setFunction(FunctionDeclaration function) {
		this.function = function;
	}
	public ArrayList<IdExpression> getIdExpressions() {
		return idExpressions;
	}
	public void setIdExpressions(ArrayList<IdExpression> idExpressions) {
		this.idExpressions = idExpressions;
	}
	
	

}
