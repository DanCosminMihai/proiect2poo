package database;

import java.util.ArrayList;

public final class AnnualChange {

  private final Double newSantaBudget;
  private final ArrayList<Gift> newGifts;
  private final ArrayList<Child> newChildren;
  private final ArrayList<ChildUpdate> childrenUpdates;

  public AnnualChange(final Double newSantaBudget, final ArrayList<Gift> newGifts,
      final ArrayList<Child> newChildren, final ArrayList<ChildUpdate> childrenUpdates) {
    this.newSantaBudget = newSantaBudget;
    this.newGifts = newGifts;
    this.newChildren = newChildren;
    this.childrenUpdates = childrenUpdates;
  }

  public Double getNewSantaBudget() {
    return newSantaBudget;
  }

  public ArrayList<Gift> getNewGifts() {
    return newGifts;
  }

  public ArrayList<Child> getNewChildren() {
    return newChildren;
  }

  public ArrayList<ChildUpdate> getChildrenUpdates() {
    return childrenUpdates;
  }

}
