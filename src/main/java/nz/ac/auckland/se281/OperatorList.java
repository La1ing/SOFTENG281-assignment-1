package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorList {

  private ArrayList<Operator> operatorList;

  // Initialising operatorList
  public OperatorList() {
    operatorList = new ArrayList<>();
  }

  public void addToList(Operator op) {
    operatorList.add(op);
  }

  // check if operatorList is empty
  public boolean noOperators() {
    return operatorList.isEmpty();
  }

  public Operator getOperator(int i) {
    return operatorList.get(i);
  }

  // Checks for duplicate entry by matching name and location
  public boolean isDuplicate(Operator op) {
    for (Operator operator : operatorList) {
      if (op.getLocation().equals(operator.getLocation())
          && op.getOperatorName().equals(operator.getOperatorName())) {
        return true;
      }
    }
    return false;
  }
}
