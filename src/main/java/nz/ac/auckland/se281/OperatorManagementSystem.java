package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private OperatorList operatorList;
  private ActivityList activityList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    this.operatorList = new OperatorList();
    this.activityList = new ActivityList();
  }

  public void searchOperators(String keyword) {

    // Case when there are no operators
    if (this.operatorList.containsNoEntries()) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      return;
    }

    // Finding amount of indexes in operatorList (improved to account for "*" case)
    ArrayList<Integer> matchingOperators = operatorList.getMatchingOperators(keyword);
    int size = matchingOperators.size();

    if (size == 1) {
      // case for 1 operator
      Operator op = this.operatorList.getEntry(matchingOperators.get(0));
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ": ");
      op.printDetails();
    } else if (size == 0) {
      // case for 0 operators found
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else {
      // case for multiple operators
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(size), "s", ": ");
      for (int i : matchingOperators) {
        Operator op = this.operatorList.getEntry(i);
        op.printDetails();
      }
    }
  }

  public void createOperator(String operatorName, String location) {

    operatorName = operatorName.trim(); // trim initial and trailing whitespaces

    // Checking for valid operator name
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // Checking for valid location
    Location locationFound = Location.fromString(location);
    if (locationFound == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    int operatorNum = 1 + operatorList.opsInSameLoc(locationFound);
    Operator operator = new Operator(operatorName, locationFound, operatorNum);

    // Creates a string of the location with the english and te reo names
    String locationString = locationFound.getFullName();

    // Creating operator ID
    operator.createOperatorId();

    // Check for duplicate entry
    if (this.operatorList.isDuplicate(operator)) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, locationString);
      return;
    }

    // Add operator to managementSystem
    this.operatorList.addToList(operator);

    MessageCli.OPERATOR_CREATED.printMessage(
        operatorName, operator.getOperatorId(), locationString);
  }

  public void viewActivities(String operatorId) {
    // Checking if operatorId is valid
    if (operatorList.searchByOperatorId(operatorId) == null) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }
    ArrayList<Integer> viewedActivities = activityList.getViewedActivities(operatorId);
    int size = viewedActivities.size(); // Finding size of viewed activities

    if (size == 0) {
      // Case for no activies found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (size == 1) {
      // Case for 1 activity found
      Activity activity = activityList.getEntry(viewedActivities.get(0));
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      activity.printDetails();
    } else {
      // Case for multiple activites found
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", Integer.toString(viewedActivities.size()), "ies", ":");
      for (int i : viewedActivities) {
        Activity activity = this.activityList.getEntry(i);
        activity.printDetails();
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    activityName = activityName.trim();
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }
    // Search for matching operatorId in OperatorList
    Operator operator = operatorList.searchByOperatorId(operatorId);

    // Case for no mathcing operator
    if (operator == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }

    // Search for matching activity type
    ActivityType typeFound = ActivityType.fromString(activityType);
    int activityNum = 1 + activityList.acsInSameOp(operatorId);

    // create activity
    Activity activity =
        new Activity(
            activityName,
            operator.getOperatorName(),
            operator.getLocation(),
            operator.getOperatorNum(),
            operatorId,
            typeFound,
            activityNum);

    activity.createActivityId();

    // NEED TO ADD ADD TO ACTIVITYLIST
    this.activityList.addToList(activity);

    MessageCli.ACTIVITY_CREATED.printMessage(
        activityName, activity.getActivityId(), typeFound.toString(), activity.getOperatorName());
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
