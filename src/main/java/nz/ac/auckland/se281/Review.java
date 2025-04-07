package nz.ac.auckland.se281;

public abstract class Review {
  protected String activityId;
  protected String name;
  protected Integer rating;
  protected String comment;

  public Review(String activityId, String name, Integer rating, String comment) {
    this.activityId = activityId;
    this.name = name;
    this.rating = rating;
    this.comment = comment;
  }
}
