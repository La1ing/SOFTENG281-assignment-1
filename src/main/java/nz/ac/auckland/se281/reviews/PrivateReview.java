package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types;

public class PrivateReview extends Review {
  private String email;
  private boolean followUp;
  private String resolved;

  public PrivateReview(
      String activityId,
      String name,
      String email,
      Integer rating,
      String comment,
      String followUp) {
    super(activityId, name, rating, comment);
    this.reviewType = Types.ReviewType.PRIVATE;
    switch (followUp) {
      case "y":
        this.followUp = true;
        break;
      case "n":
        this.followUp = false;
        break;
    }
  }

  @Override
  public void printDetails() {
    super.printDetails();
    if (this.resolved == null) {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
    }
  }
}
