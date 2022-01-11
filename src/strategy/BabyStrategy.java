package strategy;

import common.Constants;
import database.Child;

public final class BabyStrategy implements NiceScoreStrategy {

  @Override
  public Double getAverageNiceScore(final Child child) {
    return Constants.MAXSCORE;
  }
}
