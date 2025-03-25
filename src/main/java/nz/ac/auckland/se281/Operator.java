package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class Operator {

  private String operatorName;
  private Location location;
  private Integer operatorNum;
  private String operatorId;

  public Operator(String on, Location loc, Integer num) {
    this.operatorName = on;
    this.location = loc;
    this.operatorNum = num;
  }

  public String createOperatorId() {

    String operatorInitials = "";
    String locationAbbrev = this.location.getLocationAbbreviation();
    // hard coded but improved
    // this.operatorNum = 1 + opsInSameLoc();
    String threeDigitNum = String.format("%03d", this.operatorNum);

    // Creating the operator initals by splitting it and then concatenating the initial characters
    // into a string
    String[] words = this.operatorName.split(" ");

    for (String word : words) {
      char firstChar = word.charAt(0);
      // Concatenating firstChar into operatorId
      operatorInitials = operatorInitials + firstChar;
    }

    this.operatorId = operatorInitials + "-" + locationAbbrev + "-" + threeDigitNum;
    return this.operatorId;
  }

  public void printDetails() {
    MessageCli.OPERATOR_ENTRY.printMessage(
        this.operatorName, this.operatorId, this.location.getFullName());
  }

  public Location getLocation() {
    return this.location;
  }

  public String getOperatorName() {
    return this.operatorName;
  }

  public ArrayList<String> getOperatorDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(this.location.getNameEnglish());
    details.add(this.location.getNameTeReo());
    details.add(this.location.getLocationAbbreviation());
    details.add(this.operatorName);
    return details;
  }
}
