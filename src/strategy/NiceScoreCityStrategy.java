package strategy;

import database.Child;
import database.Database;
import database.Gift;
import java.util.ArrayList;

public final class NiceScoreCityStrategy implements GiftStrategy {

  private class City {

    private String city;
    private Double avgScore;

    City(final String city, final Double avgScore) {
      this.city = city;
      this.avgScore = avgScore;
    }

    public String getCity() {
      return city;
    }

    public Double getAvgScore() {
      return avgScore;
    }

  }

  @Override
  public void assignGifts(final Database database) {
    ArrayList<String> cities = new ArrayList<>();
    database.getChildren().forEach((child) -> {
      if (!cities.contains(child.getCity())) {
        cities.add(child.getCity());
      }
    });

    ArrayList<City> cityArrayList = new ArrayList<>();
    for (String city : cities) {
      Double sum = 0.0;
      Integer number = 0;
      for (int i = 0; i < database.getChildren().size(); i++) {
        if (database.getChildren().get(i).getCity().equals(city)) {
          sum += database.getChildren().get(i).getAverageScore();
          number++;
        }
      }
      cityArrayList.add(new City(city, sum / number));
    }
    cityArrayList.sort((c1, c2) -> {
      if (c2.getAvgScore().equals(c1.getAvgScore())) {
        return c1.getCity().compareTo(c2.getCity());
      }
      return c2.getAvgScore().compareTo(c1.getAvgScore());
    });

    for (City city : cityArrayList) {
      ArrayList<Child> children = new ArrayList<>(database.getChildren());
      children.removeIf((child -> !child.getCity().equals(city.getCity())));
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
}
