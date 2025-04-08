package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types;

public class PublicReview extends Review {
  private boolean anon;
  private boolean endorsed;

  public PublicReview(String activityId, String name, String anon, Integer rating, String comment) {
    super(activityId, name, rating, comment); // Inhereting from reivew parent class
    this.reviewType = Types.ReviewType.PUBLIC;
    switch (anon) {
      case "y":
        this.anon = true;
        break;
      case "n":
        this.anon = false;
        break;
    }
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

  @Override
  public void printDetails() {
    if (this.anon == true) {
      MessageCli.REVIEW_ENTRY_HEADER.printMessage(
          Integer.toString(rating), "5", this.reviewType.toString(), this.reviewId, "Anonymous");
    } else {
      MessageCli.REVIEW_ENTRY_HEADER.printMessage(
          Integer.toString(rating), "5", this.reviewType.toString(), this.reviewId, this.name);
    }

    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(this.comment);

    if (this.endorsed) {
      MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
    }
  }
}
