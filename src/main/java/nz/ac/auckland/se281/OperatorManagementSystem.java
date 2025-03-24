package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private OperatorList operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new OperatorList();
  }

  public void searchOperators(String keyword) {

    // case when there are no operators
    if (operatorList.noOperators()) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      return;
    }
    Operator op = operatorList.getOperator(0);
    // hard coding one result found
    MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ": ");
    op.printDetails();
  }

  public void createOperator(String operatorName, String location) {

    Location locationFound = Location.fromString(location);
    Operator operator = new Operator(operatorName, locationFound);

    // creates a string of the location with the english and te reo names
    String locationString = locationFound.getFullName();

    // creating operator ID
    String operatorId = operator.createOperatorId();

    // Add operator to managementSystem
    operatorList.addToList(operator);

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, locationString);
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
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
