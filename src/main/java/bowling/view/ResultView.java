package bowling.view;

import bowling.model.Name;
import bowling.model.gameresult.GameResult;
import bowling.model.gameresult.GameResults;

public class ResultView {

    private static final String BOWLING_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static void printResults(Name name, GameResults gameResults) {
        printFrameHeader();
        printName(name);
        printGameResults(gameResults);
    }

    private static void printName(Name name) {
        System.out.printf("|  %s |", name.get());
    }

    public static void printFrameHeader() {
        System.out.println(BOWLING_BOARD_HEADER);
    }

    private static void printGameResults(GameResults gameResults) {
        gameResults.getGameResults().forEach(ResultView::printGameResult);
        System.out.println();
    }

    private static void printGameResult(GameResult gameResult) {
        System.out.print(getGameResult(gameResult));
    }

    private static String getGameResult(GameResult gameResult) {
        if(gameResult.isFinalResult()) return getFinalGameResult(gameResult);
        return String.format("  %-3s |",gameResult.getStatusDesc());
    }

    private static String getFinalGameResult(GameResult gameResult) {
        return String.format(" %-5s|",gameResult.getStatusDesc());
    }

}
