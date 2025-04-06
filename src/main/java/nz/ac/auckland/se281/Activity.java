package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class Activity extends Operator {
  private int activityNum;
  private String activityId;
  private ActivityType type;
  private String operatorId;

  public Activity(String on, Location loc, Integer num, String operatorId, ActivityType type) {
    super(on, loc, num);
    this.operatorId = operatorId;
    this.type = type;
  }

  public void createActivityId() {
    activityNum = 1; // Hard coding activityNum = 1
    String threeDigitNum = String.format("%03d", this.activityNum);
    this.activityId = operatorId + "-" + threeDigitNum;
  }

  public String getActivityId() {
    return activityId;
  }

  public ActivityType getActivityType() {
    return type;
  }
}
