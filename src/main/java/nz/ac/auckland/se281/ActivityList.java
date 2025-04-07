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

  public ArrayList<Integer> getMatchingActivities(String keyword) {
    ArrayList<Integer> indexes = new ArrayList<>();

    // Case for if "*", then returns all indexes
    if (keyword.trim().equals("*")) {
      for (int i = 0; i < list.size(); i++) {
        indexes.add(i);
      }
      return indexes;
    }

    for (int i = 0; i < list.size(); i++) {
      Activity activity = list.get(i);
      ArrayList<String> details = activity.getActivityDetails();

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

  @Override
  public void printEntries(ArrayList<Integer> indexes) {
    int size = indexes.size();
    if (size == 0) {
      // Case for no activies found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (size == 1) {
      // Case for 1 activity found
      Activity activity = this.getEntry(indexes.get(0));
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      activity.printDetails();
    } else {
      // Case for multiple activites found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(indexes.size()), "ies", ":");
      for (int i : indexes) {
        Activity activity = this.getEntry(i);
        activity.printDetails();
      }
    }
  }
}
