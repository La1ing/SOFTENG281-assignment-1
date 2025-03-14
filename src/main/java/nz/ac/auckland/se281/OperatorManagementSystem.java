package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private ArrayList<Operator> managementSystem;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    managementSystem = new ArrayList<Operator>();
  }

  //addOperator test pending
  private void addOperator(Operator op) {
    managementSystem.add(op);
  }

  public void searchOperators(String keyword) {

    MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", "."); //hard coded, need fixing
  }

  public void createOperator(String operatorName, String location) {

    Location locationFound = Location.fromString(location);
    Operator operator = new Operator (operatorName, locationFound);

    String locationString = locationFound.getFullName(); // creates a string of the location with the english and te reo names
    
    //creating operator ID
    String operatorId = operator.createOperatorId();

    //Add operator to managementSystem 
    addOperator(operator);

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
