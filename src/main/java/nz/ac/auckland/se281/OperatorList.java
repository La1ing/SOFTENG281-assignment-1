package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorList extends ListTypes<Operator> {

  // Initialising operatorList
  public OperatorList() {
    super();
  }

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

  public int opsInSameLoc(Location loc) {
    int sameLoc = 0;
    for (Operator operator : this.list) {
      if (loc.equals(operator.getLocation())) {
        sameLoc++;
      }
    }
    return sameLoc;
  }

  public ArrayList<Integer> getMatchingEntries(String keyword) {
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

  public void printEntries(ArrayList<Integer> indexes) {
    int size = indexes.size();
    if (size == 1) {
      // case for 1 operator
      Operator op = this.getEntry(indexes.get(0));
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ": ");
      op.printDetails();
    } else if (size == 0) {
      // case for 0 operators found
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else {
      // case for multiple operators
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(size), "s", ": ");
      for (int i : indexes) {
        Operator op = this.getEntry(i);
        op.printDetails();
      }
    }
  }
}
