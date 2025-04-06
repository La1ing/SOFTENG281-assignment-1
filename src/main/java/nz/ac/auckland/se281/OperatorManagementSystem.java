package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private OperatorList operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    this.operatorList = new OperatorList();
  }

  public void searchOperators(String keyword) {

    // Case when there are no operators
    if (this.operatorList.containsNoOperators()) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      return;
    }

    // Finding amount of indexes in operatorList (improved to account for "*" case)
    ArrayList<Integer> matchingOperators = operatorList.getMatchingOperators(keyword);
    int size = matchingOperators.size();

    if (size == 1) {
      // case for 1 operator
      Operator op = this.operatorList.getOperator(matchingOperators.get(0));
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ": ");
      op.printDetails();
    } else if (size == 0) {
      // case for 0 operators found
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else {
      // case for multiple operators
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(size), "s", ": ");
      for (int i : matchingOperators) {
        Operator op = this.operatorList.getOperator(i);
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
    String operatorId = operator.createOperatorId();

    // Check for duplicate entry
    if (this.operatorList.isDuplicate(operator)) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, locationString);
      return;
    }

    // Add operator to managementSystem
    this.operatorList.addToList(operator);

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, locationString);
  }

  public void viewActivities(String operatorId) {
    MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    activityName = activityName.trim();

    // Search for matching operatorId in OperatorList
    if (!operatorList.containsOperatorId(operatorId)) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
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
