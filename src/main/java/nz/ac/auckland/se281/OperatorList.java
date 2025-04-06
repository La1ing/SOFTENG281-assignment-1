package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorList extends ListTypes<Operator> {

  // protected ArrayList<Operator> operatorList;

  // Initialising operatorList
  public OperatorList() {
    super();
  }

  /*public void addToList(Operator op) {
    this.operatorList.add(op);
  }*/

  // check if operatorList is empty
  /*public boolean containsNoOperators() {
    return this.operatorList.isEmpty();
  }*/

  /*public Operator getOperator(int i) {
    return this.operatorList.get(i);
  }*/

  // Checks for duplicate entry by matching name and location
  public boolean isDuplicate(Operator op) {
    for (Operator operator : this.list) {
      if (op.getLocation().equals(operator.getLocation())
          && op.getOperatorName().equalsIgnoreCase(operator.getOperatorName())) {
        return true;
      }
    }
    return false;
  }

  /*public int getSize() {
    return this.operatorList.size();
  }*/

  public int opsInSameLoc(Location loc) {
    int sameLoc = 0;
    for (Operator operator : this.list) {
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
      for (int i = 0; i < list.size(); i++) {
        indexes.add(i);
      }
      return indexes;
    }

    for (int i = 0; i < list.size(); i++) {
      Operator op = list.get(i);
      ArrayList<String> details = op.getOperatorDetails();

      for (String detail : details) {
        if (detail
            .toLowerCase()
            .contains(keyword.trim().toLowerCase())) { // Matching details (not case sensitive)
          indexes.add(i);
          break; // Moving to next iteration of detail loop
        }
      }
    }

    return indexes;
  }

  public Operator searchByOperatorId(String operatorId) {
    for (Operator operator : list) {
      if (operator.getOperatorId().equals(operatorId)) {
        return operator;
      }
    }
    return null;
  }
}
