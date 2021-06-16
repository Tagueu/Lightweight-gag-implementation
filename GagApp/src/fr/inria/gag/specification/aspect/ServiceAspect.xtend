package fr.inria.gag.specification.aspect

import fr.inria.gag.model.specification.Service

class ServiceAspect extends Service {
	
	new(){
		
	}
	
	new( Service s){
		this.name=s.name;
		this.axiom = s.axiom;
		this.inputParameters=s.inputParameters;
		this.outputParameters = s.outputParameters;
		this.rules= s.rules;
	}
}