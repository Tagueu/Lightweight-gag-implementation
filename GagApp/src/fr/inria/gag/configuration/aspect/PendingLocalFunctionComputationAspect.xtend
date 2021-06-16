package fr.inria.gag.configuration.aspect

import fr.inria.gag.model.configuration.PendingLocalFunctionComputation
import fr.inria.gag.util.EncapsulatedValue
import groovy.lang.*

class PendingLocalFunctionComputationAspect extends PendingLocalFunctionComputation {
	
	static String classPath ="E:\\PhD Recherche\\Implementation\\workspace-java\\GagApp\\bin";
	new(){
		
	}
	new(PendingLocalFunctionComputation pend){
		this.functionDeclaration=pend.functionDeclaration;
		this.actualParameters=pend.actualParameters;
		this.dataToCompute=pend.dataToCompute;
	}
	
	def boolean isExecutable() {
		var isexc =  true
		for(i:0 ..<this.actualParameters.size){
			var data =this.actualParameters.get(i);
			var ecData = data.value as EncapsulatedValue;
			if(ecData.isNull){
			   isexc=false;	
			}
		}
		return isexc;
	}
	
	def Object execute(){
		var binding = new Binding ;
		var res = null as Object;
		try {
			println("run code")
			

			val shell = new GroovyShell( /*currentClassLoader,*/ binding)
			var cl = shell.classLoader
			cl.addClasspath(classPath);
			var params="("
			for(i:0 ..<this.actualParameters.size){
				var d = this.actualParameters.get(i);
				var ecD = d.value as EncapsulatedValue;
				binding.setVariable('data'+i,ecD.value);
				if (i!=0) { params+=',' } 
				params+= 'data'+i;
				
				 
			}
			params+=')'
			// var htmlCleanedDescr = "MyCustomGAGGuard.staticIsRuleActivable()"
			var stringToExecute = this.functionDeclaration.location+"."+this.functionDeclaration.method+params;
			//Console.debug(stringToExecute);
			res = shell.evaluate(stringToExecute)  // as Map<String, Object>
//			for (OutputPin port: _self.outputs) {
//				//_self.system.sharedMemory.put(portName, res.get(portName))
//			}
		} catch (Exception cnfe) {
			println("Failed to call Groovy script " + cnfe.message)
			cnfe.printStackTrace()
		}
		return res
	
	}
	
}