package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;
import nz.ac.auckland.se281.reviews.ExpertReview;
import nz.ac.auckland.se281.reviews.PrivateReview;
import nz.ac.auckland.se281.reviews.PublicReview;

public class OperatorManagementSystem {

  private OperatorList operatorList;
  private ActivityList activityList;
  private ReviewList reviewList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    this.operatorList = new OperatorList();
    this.activityList = new ActivityList();
    this.reviewList = new ReviewList();
  }

  public void searchOperators(String keyword) {

    // Case when there are no operators
    if (this.operatorList.containsNoEntries()) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      return;
    }

    // Finding amount of indexes in operatorList (improved to account for "*" case)
    ArrayList<Integer> matchingOperators = operatorList.getMatchingEntries(keyword);
    operatorList.printEntries(matchingOperators);
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
    activityList.printEntries(viewedActivities);
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

    this.activityList.addToList(activity);

    MessageCli.ACTIVITY_CREATED.printMessage(
        activityName, activity.getActivityId(), typeFound.toString(), activity.getOperatorName());
  }

  public void searchActivities(String keyword) {
    // VIEWS BY KEYWORD IN ACTIVITYNAME / TYPE / OPERQATOR LOCATION

    // Case when there are no activities
    if (this.activityList.containsNoEntries()) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }

    // Finding amount of indexes in activityList
    ArrayList<Integer> matchingActivities = activityList.getMatchingEntries(keyword);
    activityList.printEntries(matchingActivities);
  }

  public void addPublicReview(String activityId, String[] options) {
    // Check for valid activityId
    Activity activity = activityList.getMatchingActivity(activityId);

    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    PublicReview review =
        new PublicReview(
            activityId, options[0], options[1], Integer.valueOf(options[2]), options[3]);

    String activityName = activity.getActivityName();
    Location location = activity.getLocation();
    int reviewNum = 1 + reviewList.revsInSameAct(activityId);
    // Setting the details for review (activity name, activity location, review number)
    review.setDetails(activityName, location, reviewNum, activity);

    // Add review to reviewList
    this.reviewList.addToList(review);

    // Print success message
    MessageCli.REVIEW_ADDED.printMessage(
        review.getReviewType().toString(), review.getReviewId(), activity.getActivityName());
  }

  public void addPrivateReview(String activityId, String[] options) {
    // Check for valid activityId
    Activity activity = activityList.getMatchingActivity(activityId);

    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    PrivateReview review =
        new PrivateReview(
            activityId,
            options[0],
            options[1],
            Integer.valueOf(options[2]),
            options[3],
            options[4]);

    String activityName = activity.getActivityName();
    Location location = activity.getLocation();
    int reviewNum = 1 + reviewList.revsInSameAct(activityId);
    // Setting the details for review (activity name, activity location, review number, activity)
    review.setDetails(activityName, location, reviewNum, activity);
    ;

    // Add review to reviewList
    this.reviewList.addToList(review);

    // Print success message
    MessageCli.REVIEW_ADDED.printMessage(
        review.getReviewType().toString(), review.getReviewId(), activity.getActivityName());
  }

  public void addExpertReview(String activityId, String[] options) {
    // Check for valid activityId
    Activity activity = activityList.getMatchingActivity(activityId);

    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    ExpertReview review =
        new ExpertReview(
            activityId, options[0], Integer.valueOf(options[1]), options[2], options[3]);

    String activityName = activity.getActivityName();
    Location location = activity.getLocation();
    int reviewNum = 1 + reviewList.revsInSameAct(activityId);
    // Setting the details for review (activity name, activity location, review number)
    review.setDetails(activityName, location, reviewNum, activity);

    // Add review to reviewList
    this.reviewList.addToList(review);

    // Print success message
    MessageCli.REVIEW_ADDED.printMessage(
        review.getReviewType().toString(), review.getReviewId(), activity.getActivityName());
  }

  public void displayReviews(String activityId) {
    // Check for valid activityId
    Activity activity = activityList.getMatchingActivity(activityId);

    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    // Case when there are no reviews
    if (this.reviewList.containsNoEntries()) {
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activity.getActivityName());
      return;
    }

    // Finding amount of indexes in activityList
    ArrayList<Integer> displayedReviews = reviewList.getMatchingActId(activityId);
    reviewList.printReviews(displayedReviews, activity.getActivityName());
  }

  public void endorseReview(String reviewId) {
    reviewList.settingEndorsement(reviewId);
  }

  public void resolveReview(String reviewId, String response) {
    reviewList.resolvingReview(reviewId, response);
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    reviewList.uploadingImage(reviewId, imageName);
  }

  public void displayTopActivities() {
    reviewList.rankActivities(Types.Location.AKL);
    reviewList.rankActivities(Types.Location.HLZ);
    reviewList.rankActivities(Types.Location.TRG);
    reviewList.rankActivities(Types.Location.TUO);
    reviewList.rankActivities(Types.Location.WLG);
    reviewList.rankActivities(Types.Location.NSN);
    reviewList.rankActivities(Types.Location.CHC);
    reviewList.rankActivities(Types.Location.DUD);
  }
}
