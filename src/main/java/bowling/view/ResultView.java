package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.user.User;

public class ResultView {

    private static final String SCORE_BOARD_START_FORMAT = "| NAME |";
    private static final String SCORE_BOARD_FORMAT = "  %2d  |";

    private static final String SCORE_START_FORMAT = "|  %3s |";
    private static final String SCORE_FORMAT = "  %2s  |";

    public static void printResult(User user, Frames frames) {
        printScoreBoard();
        printScore(user, frames);
    }

    private static void printScoreBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(SCORE_BOARD_START_FORMAT);
        for (int i=1; i<=10; i++){
            sb.append(String.format(SCORE_BOARD_FORMAT, i));
        }
        System.out.println(sb.toString());
    }

    private static void printScore(User user, Frames frames) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SCORE_START_FORMAT, user.nameToString()));
        for (int i=1; i<=10; i++){
            sb.append(String.format(SCORE_FORMAT, null));
        }
        System.out.println(sb.toString());
    }
}
