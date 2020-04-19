package bowling.view;

import bowling.controller.BowlingGame;
import bowling.domain.frame.state.States;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.view.format.NameFormatter;

import java.util.List;

public class OutputView {
    public static final String DELIMITER = "|";
    public static final String EMPTY_STRING = "";
    private static final String FRAMES_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private OutputView() {}

    private static void printFramesHeader() {
        System.out.println(FRAMES_HEADER);
    }

    public static void printOverHead(final String name, final List<States> states, final List<Score> scores) {
        printFramesHeader();
        printPlayerName(name);
        StateView.print(states);
        ScoreView.print(scores);
        System.out.println();
    }

    private static void printPlayerName(final String name) {
        System.out.print(formatName(name));
    }

    private static String formatName(final String name) {
        return DELIMITER + NameFormatter.format(name) + DELIMITER;
    }
}
