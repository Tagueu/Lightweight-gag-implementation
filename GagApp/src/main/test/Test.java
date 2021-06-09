package main.test;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;

import fr.inria.gag.configuration.Configuration;
import fr.inria.gag.specification.DecompositionRule;
import fr.inria.gag.specification.FunctionDeclaration;
import fr.inria.gag.specification.GAG;
import fr.inria.gag.specification.Guard;
import fr.inria.gag.specification.SemanticRule;
import fr.inria.gag.specification.Service;
import groovy.lang.*;

public class Test {

	static String classPath ="E:\\PhD Recherche\\Implementation\\workspace-java\\GagApp\\bin";
	public static void main (String[] args) {
		
		System.out.println("Hello world");
		
		Binding b =new Binding();
		b.setVariable("obj",5);
		GroovyShell gsh= new GroovyShell(b);
		GroovyClassLoader cl=gsh.getClassLoader();
		cl.addClasspath(classPath);
		//System.out.println(gsh.evaluate("main.test.Test.getFive(obj)"));
		GAG g =new GAG();
		g.setName("My GAG");
		Service S1= new Service();
		S1.setName("S1");
		Service S2= new Service();
		S2.setName("S2");
		
		DecompositionRule s1r= new DecompositionRule();
		s1r.getSubServices().add(S2);
		s1r.getSubServices().add(S1);
		Guard guardSR1= new Guard();
		guardSR1.setLocation("Util.test");
		guardSR1.setMethod("g");
		s1r.setGuard(guardSR1);
		s1r.setName("R1");
		SemanticRule sem= new SemanticRule();
		FunctionDeclaration f1 = new FunctionDeclaration();
		f1.setName("f1");
		f1.setLocation("Util.test");
		f1.setMethod("f1");
		sem.getFunctionDeclarations().add(f1);
		s1r.setSemantic(sem);
		//Equation eq1
		S1.getRules().add(s1r);
		g.getServices().add(S1);
		g.getServices().add(S2);
		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(GAG.class,Configuration.class);

			Marshaller msh = ctx.createMarshaller();
			Unmarshaller umsh = ctx.createUnmarshaller();
			msh.marshal(g, new File("C:\\Users\\TAGUEU\\Desktop\\file.xml"));
			GAG mygag= (GAG) umsh.unmarshal(new File("C:\\Users\\TAGUEU\\Desktop\\file.xml"));
			msh.marshal(mygag, new File("C:\\Users\\TAGUEU\\Desktop\\file1.xml"));
			
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	public static int getFive(int fiveObject) {
		
		return fiveObject;
	}
}
