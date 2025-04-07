package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.reviews.Review;

public class ReviewList extends ListTypes<Review> {

  public ReviewList() {
    super();
  }

  public int revsInSameAct(String activityId) {
    int sameAct = 0;
    for (Review review : this.list) {
      if (review.getActivityId().equals(activityId)) {
        sameAct++;
      }
    }
    return sameAct;
  }

  // DUMMY METHOD TO AVOID ERRORS
  public ArrayList<Integer> getMatchingEntries(String keyword) {
    ArrayList<Integer> indexes = new ArrayList<>();

    return indexes;
  }

  public ArrayList<Integer> getDisplayedReviews(String activityId) {
    ArrayList<Integer> indexes = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
      Review review = list.get(i);
      if (review.getActivityId().equals(activityId)) {
        indexes.add(i);
      }
    }
    return indexes;
  }

  public void printReviews(ArrayList<Integer> indexes, String activityName) {
    int size = indexes.size();
    if (size == 0) {
      // Case for no activies found
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activityName);
    } else if (size == 1) {
      // Case for 1 activity found
      Review review = this.getEntry(indexes.get(0));
      MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", activityName);
      review.printDetails();
    } else {
      // Case for multiple activites found
      MessageCli.REVIEWS_FOUND.printMessage(
          "are", Integer.toString(indexes.size()), "s", activityName);
      for (int i : indexes) {
        Review review = this.getEntry(i);
        review.printDetails();
      }
    }
  }
}
