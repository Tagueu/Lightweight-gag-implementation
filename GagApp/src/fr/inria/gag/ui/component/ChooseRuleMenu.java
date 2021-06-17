package fr.inria.gag.ui.component;

import javax.swing.JPopupMenu;

public class ChooseRuleMenu extends JPopupMenu{
	private GAGGraphAspect graph;

	public ChooseRuleMenu(GAGGraphAspect gag)
	{
		setLabel("Select a production");
	  this.graph= gag;	
	}
}
