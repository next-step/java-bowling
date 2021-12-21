package bowling.view;

import bowling.model.Name;
import bowling.model.gameresult.GameResult;
import bowling.model.gameresult.GameResults;

public class ResultView {

    private static final String BOWLING_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EXIT_GAME = " 게임을 종료하겠습니다.";
    private static final String NAME_PRINT_FORMAT = "|  %s |";
    private static final String NORMAL_GAME_RESULT_PRINT_FORMAT = "  %-3s |";
    private static final String FINAL_GAME_RESULT_PRINT_FORMAT = " %-5s|";


    public static void printResults(Name name, GameResults gameResults) {
        printFrameHeader();
        printName(name);
        printGameResults(gameResults);
    }

    private static void printName(Name name) {
        System.out.printf(NAME_PRINT_FORMAT, name.get());
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
        return String.format(NORMAL_GAME_RESULT_PRINT_FORMAT,gameResult.getStatusDesc());
    }

    private static String getFinalGameResult(GameResult gameResult) {
        return String.format(FINAL_GAME_RESULT_PRINT_FORMAT,gameResult.getStatusDesc());
    }

    public static void printExitGameMessage(String message) {
        System.out.println(message + EXIT_GAME);
    }
}
