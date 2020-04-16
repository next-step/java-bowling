package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String VERTICAL = "|";
    private static final String NAME = "NAME";
    private static final String SCORE_BOARD_TOP;

    static {
        String buffer = IntStream.range(1, 11)
                .mapToObj(i -> String.format("%-3s%02d  ", VERTICAL, i))
                .collect(Collectors.joining("", String.format("%-3s%s  ", VERTICAL, NAME), VERTICAL));

        SCORE_BOARD_TOP = buffer;
    }

    public static void printScoreBoardTop() {
        System.out.println(SCORE_BOARD_TOP);
    }

    private static void printPlayerName(String name) {
        System.out.print(String.format("%-3s%s   ", VERTICAL, name));
    }

    public static void printScoreBoardPlayer(BowlingGame bowlingGame) {

        Frame frame = bowlingGame.getFirstFrame();
        printPlayerName(bowlingGame.getPlayerName());

        while (!bowlingGame.isFinish() && frame != null) {
            System.out.print(String.format("%s%s", VERTICAL,
                    frame.getState().getCurrentPinsState()));

            frame = frame.getNext();
        }
        System.out.println();
    }
}
