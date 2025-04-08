package nz.ac.auckland.se281.reviews;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types;

public class ExpertReview extends Review {
  // NEED TO IMPLEMENT IMAGE UPLOADING
  private boolean recommend;
  private String images;

  public ExpertReview(
      String activityId, String name, Integer rating, String comment, String recommend) {
    super(activityId, name, rating, comment); // Inhereting from review parent class
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

  public void addImage(String image) {
    // Checking if images has no value
    if (images == null) {
      this.images = image;
    } else {
      this.images += ("," + image);
    }
  }

  @Override
  public void printDetails() {
    super.printDetails();
    // Checking if review is recommended
    if (this.recommend) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }
    // Checking for empty image
    if (this.images != null) {
      MessageCli.REVIEW_ENTRY_IMAGES.printMessage(images);
    }
  }
}
