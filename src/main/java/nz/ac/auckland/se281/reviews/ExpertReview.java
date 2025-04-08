package nz.ac.auckland.se281.reviews;

import java.util.ArrayList;
import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types;

public class ExpertReview extends Review {
  // NEED TO IMPLEMENT IMAGE UPLOADING
  private boolean recommend;
  private ArrayList<String> images;

  public ExpertReview(
      String activityId, String name, Integer rating, String comment, String recommend) {
    super(activityId, name, rating, comment);
    this.reviewType = Types.ReviewType.EXPERT;
    this.images = new ArrayList<>();
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
    this.images.add(image);
  }

  @Override
  public void printDetails() {
    super.printDetails();
    if (this.recommend) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }
    if (this.images != null) {
      String imageString = images.get(0);
      for (int i = 1; i < images.size(); i++) {
        imageString += ("," + images.get(i));
      }
      MessageCli.REVIEW_ENTRY_IMAGES.printMessage(imageString);
    }
  }
}
