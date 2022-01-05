package database;

import java.util.ArrayList;

public final class Child {

  private int id;
  private String lastName;
  private String firstName;
  private String city;
  private int age;
  private ArrayList<String> giftsPreferences;
  private Double averageScore;
  private ArrayList<Double> niceScoreHistory;
  private Double assignedBudget;
  private ArrayList<Gift> receivedGifts;

  public Child(final int id, final String lastName, final String firstName, final int age,
      final String city,
      final Double niceScore, final ArrayList<String> giftsPreferences) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.age = age;
    this.city = city;
    this.averageScore = niceScore;
    this.giftsPreferences = giftsPreferences;
  }

  public int getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public String getCity() {
    return city;
  }

  public ArrayList<String> getGiftsPreferences() {
    return giftsPreferences;
  }

  public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
    this.giftsPreferences = giftsPreferences;
  }

  public ArrayList<Double> getNiceScoreHistory() {
    return niceScoreHistory;
  }

  public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
    this.niceScoreHistory = niceScoreHistory;
  }

  public Double getAverageScore() {
    return averageScore;
  }

  public void setAverageScore(final Double averageScore) {
    this.averageScore = averageScore;
  }

  public Double getAssignedBudget() {
    return assignedBudget;
  }

  public void setAssignedBudget(final Double assignedBudget) {
    this.assignedBudget = assignedBudget;
  }

  public ArrayList<Gift> getReceivedGifts() {
    return receivedGifts;
  }

  public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
    this.receivedGifts = receivedGifts;
  }

}
