package strategy;

import database.Child;
import database.Database;
import database.Gift;
import java.util.ArrayList;

public final class NiceScoreGiftStrategy implements GiftStrategy {

  @Override
  public void assignGifts(final Database database) {
    ArrayList<Child> children = new ArrayList<>(database.getChildren());
    children.sort((c1, c2) -> {
      if (c2.getAverageScore().equals(c1.getAverageScore())) {
        return c1.getId() - c2.getId();
      } else {
        return c2.getAverageScore().compareTo(c1.getAverageScore());
      }
    });
    for (Child child : children) {
      Double budget = child.getAssignedBudget();
      child.setReceivedGifts(new ArrayList<Gift>());
      for (String pref : child.getGiftsPreferences()) {
        ArrayList<Gift> giftArrayList = new ArrayList<>(database.getSantaGiftsList());
        giftArrayList.removeIf(
            (gift) -> !gift.getCategory().equals(pref) || gift.getQuantity().equals(0));
        if (giftArrayList.size() != 0) {
          giftArrayList.sort((g1, g2) -> g1.getPrice().compareTo(g2.getPrice()));
          int i = 0;
          while (i < giftArrayList.size() && giftArrayList.get(i).getPrice() > budget) {
            i++;
          }
          if (i < giftArrayList.size() && giftArrayList.get(i).getPrice() <= budget) {
            child.getReceivedGifts().add(giftArrayList.get(i));
            giftArrayList.get(i).setQuantity(giftArrayList.get(i).getQuantity() - 1);
            budget -= giftArrayList.get(i).getPrice();
          }
        }
      }
    }
  }
}
