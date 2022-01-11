package strategy;

import database.Child;

public interface NiceScoreStrategy {

  /**
   * Calculates the averege nice score for a given child.
   *
   * @param child
   * @return Double averageScore
   */
  Double getAverageNiceScore(Child child);
}
