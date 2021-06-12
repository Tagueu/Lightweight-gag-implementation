package fr.inria.gag.configuration.aspect

import fr.inria.gag.configuration.Configuration
import fr.inria.gag.configuration.Task

class ConfigurationAspect extends Configuration{
	new(){
		
	}
	
	new(Configuration conf){
		this.pendingLocalComputations=conf.pendingLocalComputations;
		this.root = conf.root;
		
	}
	
	def String print() {
		return this.root.print();
	}
	
	
	// extension method
	def String print(Task task) {
		return new TaskAspect(task).print();
	}
	
}