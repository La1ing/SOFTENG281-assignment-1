package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types;

public class PrivateReview extends Review {
  private String email;
  private boolean followUp;
  private String response;

  public PrivateReview(
      String activityId, String name, String email, Integer rating, String comment, String follow) {
    super(activityId, name, rating, comment); // Inhereting from review parent class
    this.reviewType = Types.ReviewType.PRIVATE;
    this.email = email;
    switch (follow) {
      case "y":
        this.followUp = true;
        break;
      case "n":
        this.followUp = false;
        break;
    }
  }

  public void setResolveStatus(String response) {
    this.followUp = false;
    this.response = response;
  }

  @Override
  public void printDetails() {
    super.printDetails();

    // if followup is true, add case for email needed
    if (this.followUp == true) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(this.email);
      return;
    }
    // if followup = n, just print -
    if (this.response == null) {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(response);
    }
  }
}
