package main;

import common.Constants;
import database.Child;
import database.Database;
import database.Gift;
import factory.StrategyFactory;
import java.util.ArrayList;
import strategy.NiceScoreStrategy;

public final class Round {

  /**
   * Executes a new round of gift distributing, updating the database accordingly.
   *
   * @param roundNumber
   * @param database
   */
  public void newRound(final int roundNumber, final Database database) {
    //update database according to annualChanges
    if (roundNumber > 0) {
      database.setSantaBudget(database.getAnnualChanges().get(roundNumber - 1).getNewSantaBudget());
      database.getChildren().forEach((child) -> child.setAge(child.getAge() + 1));
      database.getChildren().removeIf((child) -> child.getAge() > Constants.EIGHTEEN);
      database.getChildren()
          .addAll(database.getAnnualChanges().get(roundNumber - 1).getNewChildren());
      database.getChildren().forEach((c) -> {
        if (c.getNiceScoreHistory() == null) {
          c.setNiceScoreHistory(new ArrayList<Double>());
          c.getNiceScoreHistory().add(c.getAverageScore());
        }
      });
      database.getSantaGiftsList()
          .addAll(database.getAnnualChanges().get(roundNumber - 1).getNewGifts());
      database.getAnnualChanges().get(roundNumber - 1).getChildrenUpdates().forEach((update) -> {
        Child child = Database.getChildById(update.getId(), database);
        if (child != null) {
          if (!update.getNiceScore().equals("null")) {
            child.getNiceScoreHistory().add(Double.parseDouble(update.getNiceScore()));
          }
          ArrayList<String> newprefs = update.getGiftPreferences();
          for (String oldpref : child.getGiftsPreferences()) {
            if (!newprefs.contains(oldpref)) {
              newprefs.add(oldpref);
            }
          }

          //ensures that the updated gifts preferences list does not contain duplicates
          ArrayList<String> p = new ArrayList<String>();
          for (String pref : newprefs) {
            if (!p.contains(pref)) {
              p.add(pref);
            }
          }
          child.setGiftsPreferences(p);

          if (!update.getElf().equals("null")) {
            child.setElf(update.getElf());
          }
        }
      });
      database.getChildren().sort((c1, c2) -> c1.getId() - c2.getId());
    }
    database.getChildren().removeIf((child) -> child.getAge() > Constants.EIGHTEEN);

    //calculates the average nicescore for each child
    database.getChildren().forEach((child) -> {
      if (roundNumber == 0) {
        child.setNiceScoreHistory(new ArrayList<Double>());
        child.getNiceScoreHistory().add(child.getAverageScore());
      }
      StrategyFactory strategyFactory = new StrategyFactory(child);
      NiceScoreStrategy strategy = strategyFactory.getStrategy();
      child.setAverageScore(strategy.getAverageNiceScore(child));
    });

    database.getChildren()
        .forEach((c) -> c.setAverageScore(c.getAverageScore() + c.getNiceScoreBonus()));

    //calculates the budget for each child
    Double sum = 0.0;
    for (int i = 0; i < database.getChildren().size(); i++) {
      sum += database.getChildren().get(i).getAverageScore();
    }
    Double budgetUnit = database.getSantaBudget() / sum;
    database.getChildren()
        .forEach((child) -> child.setAssignedBudget(child.getAverageScore() * budgetUnit));

    database.getChildren().forEach((c) -> {
      if (c.getElf().equals("black")) {
        c.setAssignedBudget(c.getAssignedBudget() - c.getAssignedBudget() * 30 / 100);
      }
      if (c.getElf().equals("pink")) {
        c.setAssignedBudget(c.getAssignedBudget() + c.getAssignedBudget() * 30 / 100);
      }
    });

    //assigns gifts to the children
    System.out.println(roundNumber);
    for (Child child : database.getChildren()) {
      Double budget = child.getAssignedBudget();
      child.setReceivedGifts(new ArrayList<Gift>());
      for (String pref : child.getGiftsPreferences()) {
        ArrayList<Gift> giftArrayList = new ArrayList<>(database.getSantaGiftsList());
        giftArrayList.removeIf(
            (gift) -> !gift.getCategory().equals(pref) || gift.getQuantity().equals(0));
        if (giftArrayList.size() != 0) {
          giftArrayList.sort((g1, g2) -> g1.getPrice().compareTo(g2.getPrice()));
          System.out.println(
              child.getLastName() + " " + child.getFirstName() + " " + giftArrayList);
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
