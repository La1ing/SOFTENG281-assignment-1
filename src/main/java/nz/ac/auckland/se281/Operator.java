package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {

  private String operatorName;
  private Location location;
  private String operatorId;

  public Operator(String on, Location loc) {
    operatorName = on;
    location = loc;
  }

  public String createOperatorId() {

    String operatorInitials = "";
    String locationAbbrev = location.getLocationAbbreviation();
    String threeDigitNum = "001"; // hard coded number, needs fixing

    // Creating the operator initals by splitting it and then concatenating the initial characters
    // into a string
    String[] words = operatorName.split(" ");

    for (String word : words) {
      char firstChar = word.charAt(0);
      // concatenating firstChar into operatorId
      operatorInitials = operatorInitials + firstChar;
    }

    operatorId = operatorInitials + "-" + locationAbbrev + "-" + threeDigitNum;
    return operatorId;
  }

  public void printDetails() {
    MessageCli.OPERATOR_ENTRY.printMessage(operatorName, operatorId, location.getFullName());
  }
}
