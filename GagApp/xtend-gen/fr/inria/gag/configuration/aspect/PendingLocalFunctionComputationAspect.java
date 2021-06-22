package fr.inria.gag.configuration.aspect;

import fr.inria.gag.model.configuration.Data;
import fr.inria.gag.model.configuration.PendingLocalFunctionComputation;
import fr.inria.gag.util.EncapsulatedValue;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class PendingLocalFunctionComputationAspect extends PendingLocalFunctionComputation {
  private static String classPath = "E:\\PhD Recherche\\Implementation\\workspace-java\\GagApp\\bin";
  
  public PendingLocalFunctionComputationAspect() {
  }
  
  public PendingLocalFunctionComputationAspect(final PendingLocalFunctionComputation pend) {
    this.setFunctionDeclaration(pend.getFunctionDeclaration());
    this.setActualParameters(pend.getActualParameters());
    this.setDataToCompute(pend.getDataToCompute());
  }
  
  public boolean isExecutable() {
    boolean isexc = true;
    int _size = this.getActualParameters().size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Data data = this.getActualParameters().get((i).intValue());
        Object _value = data.getValue();
        EncapsulatedValue ecData = ((EncapsulatedValue) _value);
        boolean _isNull = ecData.isNull();
        if (_isNull) {
          isexc = false;
        }
      }
    }
    return isexc;
  }
  
  public Object execute() {
    Binding binding = new Binding();
    Object res = ((Object) null);
    try {
      InputOutput.<String>println("run code");
      final GroovyShell shell = new GroovyShell(binding);
      GroovyClassLoader cl = shell.getClassLoader();
      cl.addClasspath(PendingLocalFunctionComputationAspect.classPath);
      String params = "(";
      int _size = this.getActualParameters().size();
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          Data d = this.getActualParameters().get((i).intValue());
          Object _value = d.getValue();
          EncapsulatedValue ecD = ((EncapsulatedValue) _value);
          binding.setVariable(("data" + i), ecD.getValue());
          if (((i).intValue() != 0)) {
            String _params = params;
            params = (_params + ",");
          }
          String _params_1 = params;
          params = (_params_1 + ("data" + i));
        }
      }
      String _params = params;
      params = (_params + ")");
      String _location = this.getFunctionDeclaration().getLocation();
      String _plus = (_location + ".");
      String _method = this.getFunctionDeclaration().getMethod();
      String _plus_1 = (_plus + _method);
      String stringToExecute = (_plus_1 + params);
      res = shell.evaluate(stringToExecute);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception cnfe = (Exception)_t;
        String _message = cnfe.getMessage();
        String _plus_2 = ("Failed to call Groovy script " + _message);
        InputOutput.<String>println(_plus_2);
        cnfe.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return res;
  }
  
  public String prettyPrint() {
    String _name = this.getFunctionDeclaration().getName();
    String _plus = ("_ " + _name);
    String result = (_plus + "(");
    int _size = this.getActualParameters().size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        String _result = result;
        String _name_1 = this.getActualParameters().get((i).intValue()).getName();
        result = (_result + _name_1);
        int _size_1 = this.getActualParameters().size();
        int _minus = (_size_1 - 1);
        boolean _notEquals = ((i).intValue() != _minus);
        if (_notEquals) {
          String _result_1 = result;
          result = (_result_1 + ",");
        }
      }
    }
    String _result = result;
    result = (_result + ")");
    return result;
  }
}
