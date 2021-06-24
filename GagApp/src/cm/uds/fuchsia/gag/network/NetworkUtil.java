package cm.uds.fuchsia.gag.network;

import java.util.ArrayList;

import cm.uds.fuchsia.gag.model.configuration.Configuration;
import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.model.specification.Service;
import cm.uds.fuchsia.gag.specification.aspect.GAGAspect;
import cm.uds.fuchsia.gag.ui.component.ComponentIHM;

public class NetworkUtil {

	
	public static void receiveTask(Task t,String componentName,GAG g, ArrayList<Subscription> subcriptionList, ComponentIHM ihm) {
		//GAGAspect gag = new GAGAspect(g);
		Task myTask= new Task();
		myTask.setInputs(t.getInputs());
		myTask.setOutputs(t.getOutputs());
		//find associate service
		Service serv = t.getService();
		Service myService=null;
		ArrayList<Service> services = g.getServices();
		for(int i=0;i<services.size();i++) {
			if(serv.getName().equals(services.get(i).getName())) {
				myService=services.get(i);
				break;
			}
		}
		// we set the configuration
		myTask.setService(myService);
		Configuration conf= new Configuration();
		conf.setRoot(myTask);
		g.setConfiguration(conf);
		
		//create output subscriptions
		for(int i=0;i<myTask.getOutputs().size();i++) {
			Subscription sub=new Subscription();
			sub.setRemote(true);
			sub.setData(myTask.getOutputs().get(i));
			sub.setComponentName(componentName);
			subcriptionList.add(sub);
		}
		
		//put the right subscription list to ihm
		
		ihm.setSubscriptionsList(subcriptionList);
		
		//draw the graph
		ihm.disposeTheGraph(g);
		
		//update the UI
		ihm.updateUI();
		
	}
	
	public static void sendTask(Task t, String componentName,ArrayList<Subscription> subcriptionList, ComponentIHM ihm) {
		
		Task myTask = t;
		// Store subscriptions to recall the remote call and to handle notifications receipt
		for(int i=0;i<myTask.getOutputs().size();i++) {
			Subscription sub=new Subscription();
			sub.setRemote(true);
			sub.setData(myTask.getOutputs().get(i));
			sub.setComponentName(componentName);
			subcriptionList.add(sub);
		}
		//update the ui
		ihm.updateUI();
		//network aspect
		
	}
	
	public static void sendNotification(Subscription sub,ArrayList<Subscription> subcriptionList) {
		
		//remove the subscription in the list
		subcriptionList.remove(sub);
		
		//network aspect
	}
	
	
	public static void receiveNotification(Data data, String componentName, ArrayList<Subscription> subcriptionList, GAG g,ComponentIHM ihm ) {
		
		
			//look for the subscriptions
			Subscription mySub=null;
			for(int i=0;i<subcriptionList.size();i++) {
				Subscription sub = subcriptionList.get(i);
				if(sub.getData().getName().equals(data.getName()) && sub.getComponentName().equals(componentName))
				{
					mySub=sub;
					break;
				}
			}
			subcriptionList.remove(mySub);
			
			//update the gag
			
			//get the data 
			Data dat = mySub.getData();
			dat.setValue(data.getValue());
		
				//draw the graph
				ihm.disposeTheGraph(g);
				
				//update the UI
				ihm.updateUI();
		
	}
}
