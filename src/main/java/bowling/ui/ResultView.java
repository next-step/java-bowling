package bowling.ui;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.States;

public class ResultView {
    private static final int LAST_FRAME = 10;
    private static final String PARTITION = "|";
    private static final String FRAME_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_STRING = "";
    private static final String FRAME_FORMAT = "  %-3s ";


    public static void printBowlingBoard(Players players) {
        System.out.println(FRAME_TITLE);

        for (Player player : players.toList()) {
            printFrameStates(player);
            printFrameScore(player);
        }
    }

    private static void printEmptyFrames(int size) {
        Stream.generate(() -> formattingString(EMPTY_STRING))
            .limit(LAST_FRAME - size)
            .forEach(System.out::print);
        System.out.println("");
    }

    private static void printFrameStates(Player player) {
        System.out.print(PARTITION + formattingString(player.name()));

        player.states().stream()
            .map(ResultView::printStates)
            .forEach(System.out::print);

        printEmptyFrames(player.currentFrameNumber());
    }

    private static String printStates(States states) {
        String expression = states.toList()
            .stream()
            .map(ResultView::replaceNull)
            .collect(Collectors.joining(PARTITION));
        return formattingString(expression);
    }

    private static void printFrameScore(Player player) {
        System.out.print(PARTITION + formattingString(EMPTY_STRING));

        player.scores().stream()
            .map(Score::score)
            .map(ResultView::formattingScore)
            .forEach(System.out::print);

        printEmptyFrames(player.scores().size());
    }

    private static String replaceNull(final State state) {
        if (state == null) {
            return EMPTY_STRING;
        }
        return state.toSymbol();
    }

    private static String formattingString(String input) {
        return String.format(FRAME_FORMAT + PARTITION, input);
    }

    private static String formattingScore(int score) {
        return String.format(FRAME_FORMAT + PARTITION, score < 0 ? "" : score);
    }
}
