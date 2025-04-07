package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class Activity extends Operator {
  private String activityName;
  private int activityNum;
  private String activityId;
  private ActivityType type;

  public Activity(
      String activityName,
      String on,
      Location loc,
      Integer num,
      String operatorId,
      ActivityType type,
      int activityNum) {
    super(on, loc, num);
    this.activityName = activityName;
    this.type = type;
    this.setOperatorId(operatorId);
    this.activityNum = activityNum;
  }

  public void createActivityId() {
    String threeDigitNum = String.format("%03d", this.activityNum);
    this.activityId = this.getOperatorId() + "-" + threeDigitNum;
  }

  public String getActivityName() {
    return activityName;
  }

  public String getActivityId() {
    return activityId;
  }

  public ActivityType getActivityType() {
    return type;
  }

  public ArrayList<String> getActivityDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(this.location.getNameEnglish());
    details.add(this.location.getNameTeReo());
    details.add(this.location.getLocationAbbreviation());
    details.add(this.activityName);
    details.add(this.getActivityType().toString());
    return details;
  }

  @Override
  public void printDetails() {
    MessageCli.ACTIVITY_ENTRY.printMessage(
        this.getActivityName(),
        this.getActivityId(),
        this.getActivityType().toString(),
        this.getOperatorName());
  }
}
