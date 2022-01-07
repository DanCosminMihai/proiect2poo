package main;

import checker.Checker;
import common.Constants;
import database.Database;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        //run the tests, one by one
        for (int number = 3; number <=3/* Constants.TESTS_NUMBER*/; number++) {
            Database database = new Database();
            String test = Integer.toString(number);
            database.readInput(test);
            Round round = new Round();
            Writer writer = new Writer();
            for (int i = 0; i <= database.getNumberOfYears(); i++) {
                round.newRound(i, database);
                writer.addToOutput(database.getChildren());
            }
            writer.writeJSON(test);
        }
        Checker.calculateScore();
    }
}
