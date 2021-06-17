package fr.inria.gag.ui.component

import fr.inria.gag.specification.aspect.GAGAspect
import com.mxgraph.util.mxPoint
import com.mxgraph.util.mxConstants
import fr.inria.gag.model.specification.GAG
import java.util.Hashtable
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout
import javax.swing.JPanel
import java.awt.BorderLayout
import fr.inria.gag.specification.aspect.OutputInterface
import java.awt.Color
import com.mxgraph.swing.util.mxSwingConstants
import com.mxgraph.layout.mxFastOrganicLayout
import fr.inria.gag.model.configuration.Configuration
import fr.inria.gag.util.Console
import fr.inria.gag.model.configuration.Task
import fr.inria.gag.model.configuration.Data
import java.awt.event.MouseListener
import java.awt.event.MouseEvent

class GAGGraphAspect extends GAGAspect implements OutputInterface,MouseListener{
	
	private CustomGraphComponent graphComponent;
    private CustomGraph graph;
    private boolean pageView;
    private JPanel panel;
	private mxHierarchicalLayout layoutForParent;
	private Hashtable<Object, Object> mapDataGraph;
	private Hashtable<Object, Object> mapGraphData;
    public static String style=mxConstants.STYLE_FILLCOLOR + "=#ffffff";
  //  public static String styleService=style+";"+mxConstants.STYLE_SHAPE+"="+mxConstants.SHAPE_IMAGE+";"+mxConstants.STYLE_IMAGE+"=/icons/png/activity.png;";
    public static String styleService=style+";"+mxConstants.STYLE_SHAPE+"="+mxConstants.SHAPE_ELLIPSE+";";
    public static String styleIN="";
    public static String styleArrowIN=mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OVAL+";"+mxConstants.STYLE_STARTARROW + "=" + mxConstants.ARROW_CLASSIC+";";
    
    public static String styleArrowOut=mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OPEN+";"+mxConstants.STYLE_STARTARROW + "=" + mxConstants.ARROW_CLASSIC+";";
    public static String styleArrowReturn= mxConstants.STYLE_EDGE + "=" + mxConstants.EDGESTYLE_ENTITY_RELATION+";"+mxConstants.STYLE_DASHED + "=1;" +  mxConstants.STYLE_DASH_PATTERN + "=3;"+mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OPEN;
    public static String styleWaitCollor=mxConstants.STYLE_STROKECOLOR+"="+"#fb0000;";
    public static String stylePendingCollor=mxConstants.STYLE_STROKECOLOR+"="+"#e8c218;";
    public static String styleFinishCollor=mxConstants.STYLE_STROKECOLOR+"="+"#18e81c;";
     
    //new style
    public static String styleArrowSun=mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OPEN+";"+mxConstants.STYLE_STARTARROW + "=" + mxConstants.ARROW_OVAL +";";
	public static String styleServiceOpen=mxConstants.STYLE_FILLCOLOR + "=#c0bfc6"+";"+mxConstants.STYLE_SHAPE+"="+mxConstants.SHAPE_ELLIPSE+";";
	public static String styleServiceInput=mxConstants.STYLE_FILLCOLOR + "=#ffffff"+";"+mxConstants.STYLE_SHAPE+"="+mxConstants.SHAPE_ELLIPSE+";"+mxConstants.STYLE_STROKECOLOR+"=green"+";";
	public static String styleServiceOutput=mxConstants.STYLE_FILLCOLOR + "=#ffffff"+";"+mxConstants.STYLE_SHAPE+"="+mxConstants.SHAPE_ELLIPSE+";"+mxConstants.STYLE_STROKECOLOR+"=red"+";";
	Object parent
	
	ChooseRuleDialog dialog
    
    new(GAG g){
    	super(g);
    	this.mapDataGraph=new Hashtable<Object,Object>
    	this.mapGraphData=new Hashtable<Object,Object>
    }
    new(){
    	this.mapDataGraph=new Hashtable<Object,Object>
    	this.mapGraphData=new Hashtable<Object,Object>
    }
    
    def CustomGraphComponent getGraphComponent() {
		return graphComponent;
	}
	def void setGraphComponent(CustomGraphComponent graphComponent) {
		this.graphComponent = graphComponent;
	}
	
	def boolean isPageView() {
		return pageView;
	}
	def void setPageView(boolean pageView) {
		this.pageView = pageView;
	}
	def JPanel getPanel() {
		return panel;
	}
	def void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	
	
	def CustomGraph getGraph() {
		return graph;
	}
	def void setGraph(CustomGraph graph) {
		this.graph = graph;
	}
	def void dispose(JPanel jpanel){
		this.panel=jpanel;
			this.dispose();
		// dispose the gag in the jpanel
	}
	def void dispose(){
		// dispose the gag in the jpanel
		this.panel.removeAll();
		this.panel.setLayout(new BorderLayout());
		 mxSwingConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
	        mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";
	        graph = new CustomGraph();
	        graph.setGAG(this);
	        parent = graph.getDefaultParent();
	        //parent = graph.insertVertex(parent, null, "", 100, 50, 80, 30,mxConstants.STYLE_FILLCOLOR+"="+"#FFFFFF");
	        graph.getModel().beginUpdate();
	         layoutForParent = new mxHierarchicalLayout(graph);
		
		
		 	var layout = new mxFastOrganicLayout(graph);
	     // set some properties
	        layout.setForceConstant( 40); // the higher, the more separated
	        layout.setDisableEdgeStyle( false); // true transforms the edges and makes them direct lines
	        
	        try{
	             if(this.configuration!=null){
	             var root = (this.configuration as Configuration).root;
	             root.draw(null);
	             //Console.debug("v is inserted");
	           }
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        finally{
	        	 layoutForParent.parentBorder = 5;
	        	 layoutForParent.parallelEdgeSpacing = 50 ;
	        	 layoutForParent.intraCellSpacing = 150;
	        	 
	        	 layoutForParent.execute(parent);
	        	 graph.getModel().endUpdate();
	        }
	        
	        // put inputs and outputs;
	        if(this.configuration!=null){
	        val servicesOpen = this.getOpenTask((this.configuration as Configuration).root);
	        for(i:0 ..<servicesOpen.size){
	        	servicesOpen.get(i).drawInputs();
	        	servicesOpen.get(i).drawOutputs();
	        }
	        }
	        
	        
	      
	        
	        graphComponent = new CustomGraphComponent(graph);
		    graphComponent.setBackground(Color.WHITE);
		    graphComponent.zoomAndCenter; 
		    
		      //translate the graph
	      
		    graph.view.translate = new mxPoint(703/2 - graph.graphBounds.width/2+40,50);
		   // mypoint.
	        //graphComponent.setSize(new Dimension(500, 500));
	        //graphComponent.setPreferredSize(new Dimension(500,500));
	        graphComponent.setBorder(null);
	        graphComponent.setAutoScroll(false);

		  	
	        graphComponent.refresh();
	     
	        graphComponent.setLayoutStructure(this);
	        graphComponent.setGAG(this);
	        
	        // add mouse listener event 
	        graphComponent.graphControl.addMouseListener(this);
	        
	        //graphComponent.repaint();
	        //System.out.println("le genre ci c'est pur dire encore quoi ?");
	        this.panel.add(graphComponent);
	        this.panel.updateUI();
	        
	        this.panel.validate();
	        this.panel.repaint();
	}
	
	def drawInputs(Task task) {
		
		val rec = graph.getCellBounds(mapDataGraph.get(task));
		for(i:0 ..<task.inputs.size){
		var data=task.inputs.get(i);
		val v=graph.insertVertex(this.parent, null, data, rec.centerX-(task.inputs.size-i+1)*25, rec.centerY+30, data.parameter.name.length()*20, 15,styleServiceInput);
		mapDataGraph.put(data,v);
		mapGraphData.put(v,data);
		}
	}
	
	def drawOutputs(Task task) {
		
		val rec = graph.getCellBounds(mapDataGraph.get(task));
		for(i:0 ..<task.outputs.size){
		var data=task.outputs.get(i);
		val v=graph.insertVertex(this.parent, null, data, rec.centerX+(i+1)*25, rec.centerY+30, data.parameter.name.length()*20,15,styleServiceOutput);
		mapDataGraph.put(data,v);
		mapGraphData.put(v,data);
		}
	}
	
	def void draw(Task task, Task parent) {
		var v = graph.insertVertex(this.parent, null, task, 400, 10, task.getService.getName().length()*20+20, 50,(task.open)?styleServiceOpen:styleService);
	             this.mapDataGraph.put(task,v);
	             mapGraphData.put(v,task);
	     if(parent!=null){
	     	graph.insertEdge(this.parent, null, "", mapDataGraph.get(parent), v,styleArrowSun);
	     }
	     for(i:0 ..< task.subTasks.size){
	             	var element = task.subTasks.get(i);
	             	element.draw(task);
	             }
	     if(task.open){
	     
	     }
	}
	
	def void drawInput(Data data, Task t) {
		
		val rec = graph.getCellBounds(mapDataGraph.get(t));
		val v=graph.insertVertex(this.parent, null, data, rec.centerX-10, rec.centerY+20, data.parameter.name.length()*20+20, 20,styleIN+mxConstants.STYLE_STROKECOLOR+"="+"#ffffff;");
		mapDataGraph.put(data,v);
		mapGraphData.put(v,data);
	}
	
	def void proceedArtefact(){
			// proceed the artefact
	}
	
	
	
	def String getTermColor(){
		// get the term color
		return "";
	}
	
	override update(GAG g) {
		this.configuration=g.configuration
		this.dispose();
	}
	
	override mouseClicked(MouseEvent e) {
		
	}
	
	override mouseEntered(MouseEvent e) {
	}
	
	override mouseExited(MouseEvent e) {
			}
	
	override mousePressed(MouseEvent e) {
	}
	
	override mouseReleased(MouseEvent e) {
		var cell = graphComponent.getCellAt(e.getX(), e.getY());
		if(cell!==null){
			var data = mapGraphData.get(cell);
			if(data !=null){
				if(data instanceof Task ){
					Console.debug("clicking on task");
					Console.debug("(X:"+e.x+", Y:"+e.y+")")
					if(this.dialog!==null)this.dialog.dispose
					this.dialog= new ChooseRuleDialog;
					
					val location=e.locationOnScreen
					dialog.setBounds(location.x,location.y,200,200);
					dialog.iconImage = null;
					dialog.graph = this;
					dialog.rulesForTask = data as Task;
					dialog.visible = true;
				}else{
					if(this.dialog!==null)this.dialog.dispose
				}
			}else{
					if(this.dialog!==null)this.dialog.dispose
				}
			
		}else{
					if(this.dialog!==null)this.dialog.dispose
				}
	}
	
}