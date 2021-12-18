package bowling.view;

import bowling.model.Name;
import bowling.model.gameresult.GameResults;

public class ResultView {

    public static void printFrameHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printResults(Name name, GameResults gameResults) {
        printFrameHeader();
        printName(name);
        printGameResults(gameResults);
    }

    private static void printGameResults(GameResults gameResults) {
        gameResults.getGameResults().forEach(gameResult -> {
            System.out.printf("  %-3s |",gameResult.getStatusDesc());
        });
        System.out.println();
    }

    private static void printName(Name name) {
        System.out.print("|  " + name.get() + " |");
    }
}
