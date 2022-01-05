package strategy;

import database.Child;

public final class TeenStrategy implements NiceScoreStrategy {

  @Override
  public Double getAverageNiceScore(final Child child) {
    Double sum = 0.0;
    for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
      sum += child.getNiceScoreHistory().get(i) * (i + 1);
    }
    return sum / (child.getNiceScoreHistory().size() * (child.getNiceScoreHistory().size() + 1)
        / 2);
  }
}
