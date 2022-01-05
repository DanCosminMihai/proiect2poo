package strategy;

import database.Child;

public final class KidStrategy implements NiceScoreStrategy {

  @Override
  public Double getAverageNiceScore(final Child child) {
    Double sum = 0.0;
    for (Double score : child.getNiceScoreHistory()) {
      sum += score;
    }
    return sum / child.getNiceScoreHistory().size();
  }
}
