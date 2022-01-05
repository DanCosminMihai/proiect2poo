package database;

import java.util.ArrayList;

public final class ChildUpdate {

  private int id;
  private String niceScore;
  private ArrayList<String> giftPreferences;

  public ChildUpdate(final int id, final String niceScore,
      final ArrayList<String> giftPreferences) {
    this.id = id;
    this.niceScore = niceScore;
    this.giftPreferences = giftPreferences;
  }

  public int getId() {
    return id;
  }

  public String getNiceScore() {
    return niceScore;
  }

  public ArrayList<String> getGiftPreferences() {
    return giftPreferences;
  }

}
