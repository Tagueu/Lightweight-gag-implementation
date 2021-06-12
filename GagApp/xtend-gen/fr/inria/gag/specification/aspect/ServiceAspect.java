package fr.inria.gag.specification.aspect;

import fr.inria.gag.specification.Service;

@SuppressWarnings("all")
public class ServiceAspect extends Service {
  public ServiceAspect() {
  }
  
  public ServiceAspect(final Service s) {
    this.setName(s.getName());
    this.setAxiom(s.isAxiom());
    this.setInputParameters(s.getInputParameters());
    this.setOutputParameters(s.getOutputParameters());
    this.setRules(s.getRules());
  }
}
