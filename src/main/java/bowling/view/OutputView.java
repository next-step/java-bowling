package bowling.view;

import bowling.domain.result.Result;
import bowling.domain.result.Results;
import bowling.domain.value.User;

public class OutputView {

    private static final String BOWLING_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME_PRINT_FORMAT = "|  %s |";
    private static final String NORMAL_GAME_RESULT_PRINT_FORMAT = "  %-3s |";
    private static final String FINAL_GAME_RESULT_PRINT_FORMAT = " %-5s|";
    private static final String EXIT_GAME = " 게임을 종료하겠습니다.";

    public void printBowlingResult(User user, Results results) {
        printHead();
        printUserName(user);
        printGameResults(results);
    }

    private void printHead() {
        System.out.println(BOWLING_BOARD_HEADER);
    }

    private void printUserName(User user) {
        System.out.print(String.format(NAME_PRINT_FORMAT, user.getName()));
    }

    private void printGameResults(Results results) {
        results.getGameResults()
                .forEach(OutputView::printGameResult);
        System.out.println();
    }

    private static void printGameResult(Result gameResult) {
        System.out.print(getGameResult(gameResult));
    }

    private static String getGameResult(Result gameResult) {
        if(gameResult.isFinalResult()) {
            return getFinalGameResult(gameResult);
        }
        return String.format(NORMAL_GAME_RESULT_PRINT_FORMAT, gameResult.getStatusMark());
    }

    private static String getFinalGameResult(Result gameResult) {
        return String.format(FINAL_GAME_RESULT_PRINT_FORMAT,gameResult.getStatusMark());
    }

    public static void printExitGameMessage(String message) {
        System.out.println(message + EXIT_GAME);
    }
}
