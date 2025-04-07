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

  // DUMMY METHOD TO AVOID ERRORS
  @Override
  public void printEntries(ArrayList<Integer> indexes) {}
}
