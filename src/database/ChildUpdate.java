package database;

import java.util.ArrayList;

public final class ChildUpdate {

  private int id;
  private String niceScore;
  private ArrayList<String> giftPreferences;
  private String elf;

  public ChildUpdate(final int id, final String niceScore,
      final ArrayList<String> giftPreferences, final String elf) {
    this.id = id;
    this.niceScore = niceScore;
    this.giftPreferences = giftPreferences;
    this.elf = elf;
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

  public String getElf() {
    return elf;
  }

  public void setElf(final String elf) {
    this.elf = elf;
  }
}
