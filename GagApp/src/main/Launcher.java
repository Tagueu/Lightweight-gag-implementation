package main;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cm.uds.fuchsia.gag.model.configuration.Configuration;
import cm.uds.fuchsia.gag.model.specification.FunctionExpression;
import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.model.specification.IdExpression;
import cm.uds.fuchsia.gag.network.NetworkInformation;
import cm.uds.fuchsia.gag.network.Subscription;
import cm.uds.fuchsia.gag.specification.aspect.GAGAspect;
import cm.uds.fuchsia.gag.ui.component.ComponentIHM;

public class Launcher {

static String classPath ="E:\\PhD Recherche\\Implementation\\workspace-java\\GagApp\\bin";
	
	public static void launchComponent(String componentName, String gagSpecificationPath) {

		JAXBContext ctx;
		try { 
			ctx= JAXBContext.newInstance(GAG.class,Configuration.class,IdExpression.class, FunctionExpression.class);
			
			Marshaller msh = ctx.createMarshaller();
			Unmarshaller umsh = ctx.createUnmarshaller();
			/*
			msh.marshal(g, new File("C:\\Users\\TAGUEU\\Desktop\\file.xml"));
			//GAG mygag= (GAG) umsh.unmarshal(new File("C:\\Users\\TAGUEU\\Desktop\\file.xml"));
			//msh.marshal(mygag, new File("C:\\Users\\TAGUEU\\Desktop\\file1.xml"));
			*/
			GAG mygag= (GAG) umsh.unmarshal(new File(gagSpecificationPath));
			GAGAspect gasp=new GAGAspect(mygag);
			ComponentIHM window = new ComponentIHM();
			window.setVisible(true);
			window.disposeTheGraph(mygag);
			window.setTitle(componentName);
			NetworkInformation.setIHM(window);
			NetworkInformation.setSUBSCRIPTION_LIST(new ArrayList<Subscription>());
			NetworkInformation.setCOMPONENT_NAME(componentName);
			NetworkInformation.setASSOCIATE_GAG(mygag);
			//gasp.runWithExternalOuputInterface(window.getGraphLayout());
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
