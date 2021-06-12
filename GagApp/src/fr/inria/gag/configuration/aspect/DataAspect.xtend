package fr.inria.gag.configuration.aspect
import fr.inria.gag.configuration.Data;
import fr.inria.gag.util.EncapsulatedValue

class DataAspect extends Data{
	
	new(){
		
	}
	new(Data d){
		this.parameter=d.parameter;
		this.value = d.value;
	}
	
	def String print() {
		var stToPrint = "?"
		var encVal = this.value as EncapsulatedValue;
		//Console.debug(encVal.toString);
		if(!encVal.^null){stToPrint=encVal.value.toString}
		return this.parameter.name+"="+stToPrint;
	}
}