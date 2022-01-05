package factory;

import common.Constants;
import database.Child;
import strategy.BabyStrategy;
import strategy.KidStrategy;
import strategy.NiceScoreStrategy;
import strategy.TeenStrategy;

public final class StrategyFactory {

  private Child child;

  public StrategyFactory(final Child child) {
    this.child = child;
  }

  /**
   * Decides what strategy to apply for a given child, depending on age.
   *
   * @return NiceScoreStrategy object
   */
  public NiceScoreStrategy getStrategy() {
    if (child.getAge() < Constants.FIVE) {
      return new BabyStrategy();
    }
    if (child.getAge() < Constants.TWELVE) {
      return new KidStrategy();
    }
    if (child.getAge() <= Constants.EIGHTEEN) {
      return new TeenStrategy();
    }
    return null;
  }
}
