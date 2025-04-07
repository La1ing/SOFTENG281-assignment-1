package nz.ac.auckland.se281.reviews;

public abstract class Review {
  protected String activityId;
  protected String name;
  protected String rating;
  protected String comment;

  public Review(String activityId, String name, String rating, String comment) {
    this.activityId = activityId;
    this.name = name;
    this.rating = rating;
    this.comment = comment;
  }
}
