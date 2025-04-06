package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ActivityList extends ListTypes<Activity> {

  public ActivityList() {
    super();
  }

  public ArrayList<Integer> getViewedActivities(String operatorId) {
    ArrayList<Integer> indexes = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
      Activity activity = list.get(i);
      if (activity.getOperatorId().equals(operatorId)) {
        indexes.add(i);
      }
    }
    return indexes;
  }

  public int acsInSameOp(String operatorId) {
    int sameOp = 0;
    for (Activity activity : this.list) {
      if (activity.getOperatorId().equals(operatorId)) {
        sameOp++;
      }
    }
    return sameOp;
  }
}
