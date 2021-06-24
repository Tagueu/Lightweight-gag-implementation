package cm.uds.fuchsia.gag.network;

import java.util.ArrayList;

import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.ui.component.ComponentIHM;

public class NetworkInformation {

	private static String COMPONENT_NAME;
	private static GAG ASSOCIATE_GAG;
	private static ComponentIHM IHM;
	private static ArrayList<Subscription> SUBSCRIPTION_LIST;

	public static String getCOMPONENT_NAME() {
		return COMPONENT_NAME;
	}

	public static void setCOMPONENT_NAME(String cOMPONENT_NAME) {
		COMPONENT_NAME = cOMPONENT_NAME;
	}

	public static GAG getASSOCIATE_GAG() {
		return ASSOCIATE_GAG;
	}

	public static void setASSOCIATE_GAG(GAG aSSOCIATE_GAG) {
		ASSOCIATE_GAG = aSSOCIATE_GAG;
	}

	public static ComponentIHM getIHM() {
		return IHM;
	}

	public static void setIHM(ComponentIHM iHM) {
		IHM = iHM;
	}

	public static ArrayList<Subscription> getSUBSCRIPTION_LIST() {
		return SUBSCRIPTION_LIST;
	}

	public static void setSUBSCRIPTION_LIST(ArrayList<Subscription> sUBSCRIPTION_LIST) {
		SUBSCRIPTION_LIST = sUBSCRIPTION_LIST;
	}
	
	
	
	

	
	
	
}
