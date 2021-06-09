package fr.inria.gag.specification;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="action")
public class Equation {
	
	private IdExpression leftpart;
	private Expression rightpart;
	

	public IdExpression getLeftpart() {
		return leftpart;
	}
	public void setLeftpart(IdExpression leftpart) {
		this.leftpart = leftpart;
	}
	public Expression getRightpart() {
		return rightpart;
	}
	public void setRightpart(Expression rightpart) {
		this.rightpart = rightpart;
	}
	
}
