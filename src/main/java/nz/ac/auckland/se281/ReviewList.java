package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;
import nz.ac.auckland.se281.reviews.ExpertReview;
import nz.ac.auckland.se281.reviews.PrivateReview;
import nz.ac.auckland.se281.reviews.PublicReview;
import nz.ac.auckland.se281.reviews.Review;

public class ReviewList extends ListTypes<Review> {

  public ReviewList() {
    super();
  }

  public int revsInSameAct(String activityId) {
    int sameAct = 0;
    for (Review review : this.list) {
      // Checking if activitiyId matches
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

  public void settingEndorsement(String reviewId) {
    for (Review review : this.list) {
      if (review.getReviewId().equals(reviewId)) {
        if (review instanceof PublicReview) {
          // Case for if review is public
          ((PublicReview) review).addEndorsement();
          MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
          return;
        } else {
          // If review found but not public
          MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
          return;
        }
      }
    }
    // No review found for reviewid
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void resolvingReview(String reviewId, String response) {
    for (Review review : this.list) {
      if (review.getReviewId().equals(reviewId)) {
        if (review instanceof PrivateReview) {
          // Case for if review is private
          ((PrivateReview) review).setResolveStatus(response);
          MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
          return;
        } else {
          // If review found but not private
          MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
          return;
        }
      }
    }
    // No review found for reviewid
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void uploadingImage(String reviewId, String image) {
    for (Review review : this.list) {
      if (review.getReviewId().equals(reviewId)) {
        if (review instanceof ExpertReview) {
          // Case for if review is expert
          ((ExpertReview) review).addImage(image);
          MessageCli.REVIEW_IMAGE_ADDED.printMessage(image, reviewId);
          return;
        } else {
          // If review found but not expert
          MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
          return;
        }
      }
    }
    // No review found for reviewid
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public ArrayList<Integer> getMatchingActId(String activityId) {
    ArrayList<Integer> indexes = new ArrayList<>();

    // Iterating through list to find matching activityId
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

  public void rankActivities(Location location) {
    ArrayList<Review> matchingReviews = new ArrayList<>();
    ArrayList<Activity> matchingActivities = new ArrayList<>();

    for (Review review : this.list) {
      // Checking if review is not private and if location mathces
      if (!review.getReviewType().equals(Types.ReviewType.PRIVATE)
          && review.getlocation().equals(location)) {
        matchingReviews.add(review);
        // Checking if activity has been in by matchingActivities
        if (!matchingActivities.contains(review.getActivity())) {
          matchingActivities.add(review.getActivity());
        }
      }
    }
    // Checking if matching reviews is empty
    if (matchingReviews.isEmpty()) {
      MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.toString());
      return;
    }

    // Adds rating to activity
    for (Review review : matchingReviews) {
      review.getActivity().addRating(review.getRating());
    }

    // Finding highest rated activity
    Activity highestRated = null;
    double highestRating = 0;
    for (Activity activity : matchingActivities) {
      if (activity.getAverageRating() > highestRating) {
        highestRated = activity;
        highestRating = activity.getAverageRating();
      }
    }

    String ratingString = String.format("%.1f", highestRating);

    MessageCli.TOP_ACTIVITY.printMessage(
        location.toString(), highestRated.getActivityName(), ratingString);
  }
}
