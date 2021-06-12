package fr.inria.gag.specification.aspect;

import com.google.common.base.Objects;
import fr.inria.gag.configuration.Configuration;
import fr.inria.gag.configuration.Data;
import fr.inria.gag.configuration.PendingLocalFunctionComputation;
import fr.inria.gag.configuration.Task;
import fr.inria.gag.configuration.aspect.ConfigurationAspect;
import fr.inria.gag.configuration.aspect.PendingLocalFunctionComputationAspect;
import fr.inria.gag.specification.DecompositionRule;
import fr.inria.gag.specification.Equation;
import fr.inria.gag.specification.Expression;
import fr.inria.gag.specification.FunctionExpression;
import fr.inria.gag.specification.GAG;
import fr.inria.gag.specification.Guard;
import fr.inria.gag.specification.IdExpression;
import fr.inria.gag.specification.RuntimeData;
import fr.inria.gag.specification.Service;
import fr.inria.gag.util.Console;
import fr.inria.gag.util.EncapsulatedValue;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;

@SuppressWarnings("all")
public class GAGAspect extends GAG {
  public GAGAspect() {
  }
  
  public GAGAspect(final GAG g) {
    this.setName(g.getName());
    this.setConfiguration(g.getConfiguration());
    this.setServices(g.getServices());
  }
  
  public void initExecution() {
    Configuration _configuration = new Configuration();
    this.setConfiguration(_configuration);
  }
  
  public void run() {
    this.initExecution();
    RuntimeData _configuration = this.getConfiguration();
    final Configuration conf = ((Configuration) _configuration);
    this.chooseTheAxiom();
    ArrayList<Task> openTask = this.getOpenTask(conf.getRoot());
    while ((openTask.size() != 0)) {
      {
        Task task = this.chooseTask(openTask);
        DecompositionRule rule = this.chooseRule(task);
        boolean _notEquals = (!Objects.equal(rule, null));
        if (_notEquals) {
          this.applyRule(task, rule);
        }
        String _print = this.print(conf);
        String _plus = ("La configuration resultante est " + _print);
        Console.debug(_plus);
        openTask = this.getOpenTask(conf.getRoot());
      }
    }
    Console.debug("Exécution terminée !");
  }
  
  public void chooseTheAxiom() {
    final ArrayList<Service> services = this.getServices();
    final ArrayList<Service> axioms = new ArrayList<Service>();
    int _size = services.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final Service element = services.get((i).intValue());
        Boolean _isAxiom = element.isAxiom();
        if ((_isAxiom).booleanValue()) {
          axioms.add(services.get((i).intValue()));
        }
      }
    }
    Console.debug("Veuillez choisir le service axiome de démarrage parmi les services suivants : ");
    String txtAf = "";
    int _size_1 = axioms.size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        Service _get = axioms.get((i_1).intValue());
        final Service element = ((Service) _get);
        String _plus = (Integer.valueOf(((i_1).intValue() + 1)) + "- ");
        String _name = element.getName();
        String _plus_1 = (_plus + _name);
        Console.debug(_plus_1);
      }
    }
    final String choice = Console.readConsoleLine(txtAf);
    final int id = Integer.parseInt(choice);
    Service _get = axioms.get((id - 1));
    final Service serviceChoice = ((Service) _get);
    RuntimeData _configuration = this.getConfiguration();
    final Configuration conf = ((Configuration) _configuration);
    Task _task = new Task();
    conf.setRoot(_task);
    Task _root = conf.getRoot();
    _root.setService(serviceChoice);
    Console.debug("Veuillez fournir les valeurs des entrées de l\'axiome ");
    int _size_2 = conf.getRoot().getService().getInputParameters().size();
    ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
    for (final Integer i_2 : _doubleDotLessThan_2) {
      {
        Data data = new Data();
        data.setParameter(conf.getRoot().getService().getInputParameters().get((i_2).intValue()));
        String _name = data.getParameter().getName();
        String _plus = ("Veuillez entrer la valeur du paramètre " + _name);
        Console.debug(_plus);
        String _readConsoleLine = Console.readConsoleLine("");
        EncapsulatedValue _encapsulatedValue = new EncapsulatedValue(_readConsoleLine);
        data.setValue(_encapsulatedValue);
        conf.getRoot().getInputs().add(data);
      }
    }
    int _size_3 = conf.getRoot().getService().getOutputParameters().size();
    ExclusiveRange _doubleDotLessThan_3 = new ExclusiveRange(0, _size_3, true);
    for (final Integer i_3 : _doubleDotLessThan_3) {
      {
        Data data = new Data();
        data.setParameter(conf.getRoot().getService().getOutputParameters().get((i_3).intValue()));
        EncapsulatedValue _encapsulatedValue = new EncapsulatedValue();
        data.setValue(_encapsulatedValue);
        conf.getRoot().getOutputs().add(data);
      }
    }
  }
  
  public Task chooseTask(final ArrayList<Task> openTasks) {
    Console.debug("Veuillez choisir la tâche à traiter parmi les tâches suivantes : ");
    String txtAf = "";
    int _size = openTasks.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final Task element = openTasks.get((i).intValue());
        String _plus = (Integer.valueOf(((i).intValue() + 1)) + "- ");
        String _name = element.getService().getName();
        String _plus_1 = (_plus + _name);
        Console.debug(_plus_1);
      }
    }
    final String choice = Console.readConsoleLine(txtAf);
    final int id = Integer.parseInt(choice);
    return openTasks.get((id - 1));
  }
  
  public DecompositionRule chooseRule(final Task t) {
    ArrayList<DecompositionRule> applicableRules = new ArrayList<DecompositionRule>();
    int _size = t.getService().getRules().size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final DecompositionRule element = t.getService().getRules().get((i).intValue());
        Guard guard = element.getGuard();
        if ((Objects.equal(guard, null) || this.isApplicable(guard, t))) {
          applicableRules.add(element);
        }
      }
    }
    int _size_1 = applicableRules.size();
    boolean _notEquals = (_size_1 != 0);
    if (_notEquals) {
      Console.debug("Veuillez choisir la règle de décomposition à appliquer parmi les règles suivantes : ");
      String txtAf = "";
      int _size_2 = applicableRules.size();
      ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_2, true);
      for (final Integer i_1 : _doubleDotLessThan_1) {
        String _plus = (Integer.valueOf(((i_1).intValue() + 1)) + "- ");
        String _name = applicableRules.get((i_1).intValue()).getName();
        String _plus_1 = (_plus + _name);
        Console.debug(_plus_1);
      }
      final String choice = Console.readConsoleLine(txtAf);
      final int id = Integer.parseInt(choice);
      final DecompositionRule rule = applicableRules.get((id - 1));
      return rule;
    } else {
      Console.debug("Aucune règle de décomposition n\'est actuellement applicable pour cette tâche ");
      Console.readConsoleLine("");
      return null;
    }
  }
  
  public ArrayList<Task> getOpenTask(final Task root) {
    final ArrayList<Task> openTasks = new ArrayList<Task>();
    boolean _isOpen = root.isOpen();
    if (_isOpen) {
      openTasks.add(root);
    } else {
      int _size = root.getSubTasks().size();
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          final Task element = root.getSubTasks().get((i).intValue());
          final ArrayList<Task> subOpenTasks = this.getOpenTask(element);
          openTasks.addAll(subOpenTasks);
        }
      }
    }
    return openTasks;
  }
  
  public void applyRule(final Task t, final DecompositionRule r) {
    t.setAppliedRule(r.getName());
    t.setOpen(false);
    int _size = r.getSubServices().size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final Service element = r.getSubServices().get((i).intValue());
        Task st = new Task();
        this.initTask(st, element);
        t.getSubTasks().add(st);
      }
    }
    RuntimeData _configuration = this.getConfiguration();
    Configuration conf = ((Configuration) _configuration);
    ArrayList<Task> context = new ArrayList<Task>();
    context.add(t);
    context.addAll(t.getSubTasks());
    int _size_1 = r.getSemantic().getEquations().size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        Equation eq = r.getSemantic().getEquations().get((i_1).intValue());
        String _serviceName = eq.getLeftpart().getServiceName();
        String _parameterName = eq.getLeftpart().getParameterName();
        String[] ref1 = new String[] { _serviceName, _parameterName };
        Data data1 = this.findReference(ref1, context);
        Expression _rightpart = eq.getRightpart();
        if ((_rightpart instanceof IdExpression)) {
          Data data2 = ((Data) null);
          Expression _rightpart_1 = eq.getRightpart();
          final IdExpression rightPartIdExpression = ((IdExpression) _rightpart_1);
          String _serviceName_1 = rightPartIdExpression.getServiceName();
          String _parameterName_1 = rightPartIdExpression.getParameterName();
          final String[] ref2 = new String[] { _serviceName_1, _parameterName_1 };
          data2 = this.findReference(ref2, context);
          Object _value = data1.getValue();
          EncapsulatedValue ecData1 = ((EncapsulatedValue) _value);
          Object _value_1 = data2.getValue();
          ecData1.addReference(((EncapsulatedValue) _value_1));
        } else {
          Expression _rightpart_2 = eq.getRightpart();
          FunctionExpression func = ((FunctionExpression) _rightpart_2);
          Object _value_2 = data1.getValue();
          EncapsulatedValue ecData1_1 = ((EncapsulatedValue) _value_2);
          PendingLocalFunctionComputation runningFunction = new PendingLocalFunctionComputation();
          runningFunction.setDataToCompute(data1);
          runningFunction.setFunctionDeclaration(func.getFunction());
          int _size_2 = func.getIdExpressions().size();
          ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
          for (final Integer k : _doubleDotLessThan_2) {
            {
              IdExpression elId = func.getIdExpressions().get((k).intValue());
              String _serviceName_2 = elId.getServiceName();
              String _parameterName_2 = elId.getParameterName();
              final String[] ref = new String[] { _serviceName_2, _parameterName_2 };
              Data data = this.findReference(ref, context);
              runningFunction.getActualParameters().add(data);
            }
          }
          conf.getPendingLocalComputations().add(runningFunction);
        }
      }
    }
    this.computeFunction(conf.getPendingLocalComputations());
  }
  
  public Data findReference(final String[] ref, final ArrayList<Task> tasks) {
    Data objectRef = ((Data) null);
    String serviceName = (ref[0]).toString().trim();
    String serviceParameter = (ref[1]).toString().trim();
    int _size = tasks.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Task element = tasks.get((i).intValue());
        boolean _equals = element.getService().getName().equals(serviceName);
        if (_equals) {
          int _size_1 = element.getInputs().size();
          ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
          for (final Integer j : _doubleDotLessThan_1) {
            boolean _equals_1 = element.getInputs().get((j).intValue()).getParameter().getName().equals(serviceParameter);
            if (_equals_1) {
              objectRef = element.getInputs().get((j).intValue());
            }
          }
          int _size_2 = element.getOutputs().size();
          ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
          for (final Integer j_1 : _doubleDotLessThan_2) {
            boolean _equals_2 = element.getOutputs().get((j_1).intValue()).getParameter().getName().equals(serviceParameter);
            if (_equals_2) {
              objectRef = element.getOutputs().get((j_1).intValue());
            }
          }
        }
      }
    }
    return objectRef;
  }
  
  public void computeFunction(final List<PendingLocalFunctionComputation> runningFunctions) {
    ArrayList<PendingLocalFunctionComputation> executableFunctions = new ArrayList<PendingLocalFunctionComputation>();
    int _size = runningFunctions.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        PendingLocalFunctionComputation func = runningFunctions.get((i).intValue());
        Boolean _isExecutable = this.isExecutable(func);
        if ((_isExecutable).booleanValue()) {
          executableFunctions.add(func);
        }
      }
    }
    while ((executableFunctions.size() != 0)) {
      {
        int _size_1 = executableFunctions.size();
        ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
        for (final Integer i_1 : _doubleDotLessThan_1) {
          {
            PendingLocalFunctionComputation elFunc = executableFunctions.get((i_1).intValue());
            Object result = this.execute(elFunc);
            Object _value = elFunc.getDataToCompute().getValue();
            EncapsulatedValue ecObj = ((EncapsulatedValue) _value);
            ecObj.setValue(result);
          }
        }
        runningFunctions.removeAll(executableFunctions);
        ArrayList<PendingLocalFunctionComputation> _arrayList = new ArrayList<PendingLocalFunctionComputation>();
        executableFunctions = _arrayList;
        int _size_2 = runningFunctions.size();
        ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
        for (final Integer i_2 : _doubleDotLessThan_2) {
          {
            PendingLocalFunctionComputation func = runningFunctions.get((i_2).intValue());
            Boolean _isExecutable = this.isExecutable(func);
            if ((_isExecutable).booleanValue()) {
              executableFunctions.add(func);
            }
          }
        }
      }
    }
  }
  
  public void initTask(final Task t, final Service s) {
    t.setService(s);
    t.setOpen(true);
    int _size = s.getInputParameters().size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Data data = new Data();
        data.setParameter(s.getInputParameters().get((i).intValue()));
        EncapsulatedValue _encapsulatedValue = new EncapsulatedValue();
        data.setValue(_encapsulatedValue);
        t.getInputs().add(data);
      }
    }
    int _size_1 = s.getOutputParameters().size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        Data data = new Data();
        data.setParameter(s.getOutputParameters().get((i_1).intValue()));
        EncapsulatedValue _encapsulatedValue = new EncapsulatedValue();
        data.setValue(_encapsulatedValue);
        t.getOutputs().add(data);
      }
    }
  }
  
  public boolean isApplicable(final Guard guard, final Task task) {
    GuardAspect guardAspect = new GuardAspect(guard);
    return guardAspect.isApplicable(task);
  }
  
  public Boolean isExecutable(final PendingLocalFunctionComputation computation) {
    return Boolean.valueOf(new PendingLocalFunctionComputationAspect(computation).isExecutable());
  }
  
  public Object execute(final PendingLocalFunctionComputation computation) {
    return new PendingLocalFunctionComputationAspect(computation).execute();
  }
  
  public String print(final Configuration configuration) {
    return new ConfigurationAspect(configuration).print();
  }
}
