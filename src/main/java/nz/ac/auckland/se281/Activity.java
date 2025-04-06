package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Activity extends Operator {
  private int activityNum;
  private String activityId;

  public Activity(String on, Location loc, Integer num) {
    super(on, loc, num);
  }
}
