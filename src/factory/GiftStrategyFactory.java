package factory;

import strategy.GiftStrategy;
import strategy.IdStrategy;
import strategy.NiceScoreCityStrategy;
import strategy.NiceScoreGiftStrategy;


public final class GiftStrategyFactory {

  private String strategy;

  public GiftStrategyFactory(final String strategy) {
    this.strategy = strategy;
  }

  /**
   * Creates an appropriate gift distribution strategy for a database
   * @return GiftStrategy
   */

  public GiftStrategy getStrategy() {
    switch (this.strategy) {
      case "id" -> {
        return new IdStrategy();
      }
      case "niceScore" -> {
        return new NiceScoreGiftStrategy();

      }
      case "niceScoreCity" -> {
        return new NiceScoreCityStrategy();
      }
      default -> {
        return null;
      }
    }
  }
}
