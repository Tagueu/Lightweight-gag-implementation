package cm.uds.fuchsia.gag.model.specification;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;


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
	@XmlElement(name="arg")
	public ArrayList<IdExpression> getIdExpressions() {
		return idExpressions;
	}
	public void setIdExpressions(ArrayList<IdExpression> idExpressions) {
		this.idExpressions = idExpressions;
	}
	
	

}
