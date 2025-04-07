package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.Types.ReviewType;

public class PublicReview extends Review {
  private ReviewType reviewType;
  private boolean anon;
  private boolean endorsed;

  public PublicReview(
      ReviewType reviewType,
      String activityId,
      String name,
      String anon,
      Integer rating,
      String comment) {
    super(activityId, name, rating, comment);
    this.reviewType = reviewType;
    switch (anon) {
      case "y":
        this.anon = true;
        break;
      case "n":
        this.anon = false;
        break;
    }
  }

  public ReviewType getReviewType() {
    return reviewType;
  }

  public void addEndorsement() {
    this.endorsed = true;
  }

  public boolean getEndorsement() {
    return this.endorsed;
  }

  public boolean getAnon() {
    return this.anon;
  }
}
