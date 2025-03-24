package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorList {

  private ArrayList<Operator> operatorList;

  public OperatorList() {
    operatorList = new ArrayList<>();
  }

  public void addToList(Operator op) {
    operatorList.add(op);
  }

  public boolean noOperators() {
    return operatorList.isEmpty();
  }

  public Operator getOperator(int i) {
    return operatorList.get(i);
  }
}
