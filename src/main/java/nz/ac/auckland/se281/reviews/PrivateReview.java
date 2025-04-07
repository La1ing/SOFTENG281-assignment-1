package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.Types;
import nz.ac.auckland.se281.Types.ReviewType;

public class PrivateReview extends Review {
  private ReviewType reviewType;
  private String email;
  private boolean followUp;
  private boolean resolved;

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

  public ReviewType getReviewType() {
    return this.reviewType;
  }
}
