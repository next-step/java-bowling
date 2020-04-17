package bowling.view;

import bowling.controller.BowlingGame;
import bowling.domain.player.Player;
import bowling.view.format.NameFormatter;

public class OutputView {
    public static final String DELIMITER = "|";
    public static final String EMPTY_STRING = "";
    private static final String FRAMES_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private OutputView() {}

    private static void printFramesHeader() {
        System.out.println(FRAMES_HEADER);
    }

    public static void printOverHead(BowlingGame game) {
        printFramesHeader();
        printPlayerName(game.getCurrentPlayer());
        StateView.print(game.getStates());
        ScoreView.print(game.getScores());
        System.out.println();
    }

    private static void printPlayerName(final Player currentPlayer) {
        System.out.print(formatName(currentPlayer.name()));
    }

    private static String formatName(final String name) {
        return DELIMITER + NameFormatter.format(name) + DELIMITER;
    }
}
