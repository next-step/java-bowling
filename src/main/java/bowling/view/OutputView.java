package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.result.BowlingResult;
import bowling.domain.value.User;

public class OutputView {

    private static final String BOWLING_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME_PRINT_FORMAT = "|  %s |";
    private static final String NORMAL_GAME_RESULT_PRINT_FORMAT = "  %-3s |";
    private static final int START_FRAME = 1;
    private static final int FINAL_FRAME = 10;

    public void printBowlingResult(User user, BowlingResult result) {
        printHead();
        printMark(result, user);
    }

    private void printHead() {
        System.out.println(BOWLING_BOARD_HEADER);
    }

    private void printMark(BowlingResult result, User user) {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, user.getName());

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            Frame frame = result.getFrames(i);
            printFrame(bowlingBuilder, frame.getMark());
        }

        System.out.println(bowlingBuilder);
    }

    private void printPlayerName(StringBuilder bowlingBuilder, String playerName) {
        bowlingBuilder.append(String.format(NAME_PRINT_FORMAT, playerName));
    }

    private void printFrame(StringBuilder bowlingBuilder, String frameNumber) {
        bowlingBuilder.append(String.format(NORMAL_GAME_RESULT_PRINT_FORMAT, frameNumber));
    }

}
