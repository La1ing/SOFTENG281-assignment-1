package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types;

public class ExpertReview extends Review {
  // NEED TO IMPLEMENT IMAGE UPLOADING
  private boolean recommend;
  private String image;

  public ExpertReview(
      String activityId, String name, Integer rating, String comment, String recommend) {
    super(activityId, name, rating, comment);
    this.reviewType = Types.ReviewType.EXPERT;
    switch (recommend) {
      case "y":
        this.recommend = true;
        break;
      case "n":
        this.recommend = false;
        break;
    }
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public void printDetails() {
    super.printDetails();
    if (this.recommend) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }
  }
}
