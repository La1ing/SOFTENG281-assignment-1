package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types.ReviewType;

public abstract class Review {
  protected String activityId;
  protected String name;
  protected Integer rating;
  protected String comment;
  protected int reviewNum;
  protected String reviewId;
  protected ReviewType reviewType;

  public Review(String activityId, String name, Integer rating, String comment) {
    this.activityId = activityId;
    this.name = name;
    if (rating < 1) {
      this.rating = 1;
    } else if (rating > 5) {
      this.rating = 5;
    } else {
      this.rating = rating;
    }
    this.comment = comment;
  }

  public String getActivityId() {
    return this.activityId;
  }

  public void setReviewId(int reviewNum) {
    this.reviewNum = reviewNum;
    this.reviewId = this.activityId + "-R" + Integer.toString(reviewNum);
  }

  public String getReviewId() {
    return this.reviewId;
  }

  public ReviewType getReviewType() {
    return this.reviewType;
  }

  public void printDetails() {
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(
        Integer.toString(rating), "5", this.reviewType.toString(), this.reviewId, this.name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(this.comment);
  }
}
