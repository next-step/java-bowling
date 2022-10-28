package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Records;
import bowling.domain.Scores;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static String INIT_SCORE_BOARD_STRING = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String NAME_STRING = "|  %s |";
    private static String STRIKE_STRING = "  X   |";
    private static String SPARE_STRING = "  %s|/ |";
    private static String GUTTER_STRING = "-";
    private static String FIRST_PITCH_STRING = "  %s|";
    private static String SECOND_PITCH_STRING = "  %s|%s |";

    public static void printScoreBoard(BowlingGame game) {
        System.out.println(INIT_SCORE_BOARD_STRING);
        System.out.printf(NAME_STRING, game.getPlayer().getName());
        printScore(game.getRecords());
        System.out.println();
    }

    private static void printScore(Records records) {
        for (Frame frame : records.getFrames()) {
            printScore(frame.getScores());
        }
    }

    private static void printScore(Scores scores) {
        if (scores.isStrike()) {
            System.out.print(STRIKE_STRING);
            return;
        }

        List<String> downPins = getDownPinsString(scores);
        if (scores.isSpare()) {
            System.out.printf(SPARE_STRING, downPins.get(0));
            return;
        }

        if (downPins.size() == 1) {
            System.out.printf(FIRST_PITCH_STRING, downPins.get(0));
            return;
        }

        if (downPins.size() == 2) {
            System.out.printf(SECOND_PITCH_STRING, downPins.get(0), downPins.get(1));
            return;
        }
    }

    private static List<String> getDownPinsString(Scores scores) {
        return scores.getDownPins()
                .stream()
                .map(ResultView::convertScore)
                .collect(Collectors.toList());
    }

    private static String convertScore(int score) {
        if (score == 0) {
            return GUTTER_STRING;
        }
        return String.valueOf(score);
    }
}
