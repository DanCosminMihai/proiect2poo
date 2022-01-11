package strategy;

import database.Database;

public interface GiftStrategy {

  /**
   * Distributes gifts to  database of children.
   * @param database
   */
  void assignGifts(Database database);

}
