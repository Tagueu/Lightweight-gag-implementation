
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
		System.out.println(gsh.evaluate("main.test.Test.getFive(obj)"));
	}
	
	public static int getFive(int fiveObject) {
		
		return fiveObject;
	}
}
