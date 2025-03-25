package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorList {

  private ArrayList<Operator> operatorList;

  // Initialising operatorList
  public OperatorList() {
    this.operatorList = new ArrayList<>();
  }

  public void addToList(Operator op) {
    this.operatorList.add(op);
  }

  // check if operatorList is empty
  public boolean noOperators() {
    return this.operatorList.isEmpty();
  }

  public Operator getOperator(int i) {
    return this.operatorList.get(i);
  }

  // Checks for duplicate entry by matching name and location
  public boolean isDuplicate(Operator op) {
    for (Operator operator : this.operatorList) {
      if (op.getLocation().equals(operator.getLocation())
          && op.getOperatorName().equals(operator.getOperatorName())) {
        return true;
      }
    }
    return false;
  }

  public int getSize() {
    return this.operatorList.size();
  }

  public int opsInSameLoc(Location loc) {
    int sameLoc = 0;
    for (Operator operator : this.operatorList) {
      if (loc.equals(operator.getLocation())) {
        sameLoc++;
      }
    }
    return sameLoc;
  }

  public ArrayList<Integer> getMatchingOperators(String keyword) {
    ArrayList<Integer> indexes = new ArrayList<>();

    // Case for if "*", then returns all indexes
    if (keyword.trim().equals("*")) {
      for (int i = 0; i < operatorList.size(); i++) {
        indexes.add(1);
      }
      return indexes;
    }
    // SEARCH NEEDS TO BE IMPLEMENTED
    // for(Operator op : operatorList){
    //   if (){

    //   }
    // }

    return indexes;
  }
}
