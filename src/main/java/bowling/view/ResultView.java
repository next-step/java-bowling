package bowling.view;

import bowling.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static String INIT_SCORE_BOARD_STRING = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String NAME_STRING = "|  %s |";
    private static String GUTTER_STRING = "-";
    private static String TOTAL_SCORE_STRING = "\n|      |";
    private static String TOTAL_SCORE_RIGHT_STRING = "  |";
    private static String DOUBLE_BLANK = "  ";

    public static void printScoreBoard(BowlingGame game) {
        System.out.println(INIT_SCORE_BOARD_STRING);
        System.out.printf(NAME_STRING, game.getPlayer().getName());
        printPinScore(game.getRecords());
        printTotalScores(game.getRecords());
        System.out.println();
    }

    private static void printTotalScores(Records records) {
        System.out.print(TOTAL_SCORE_STRING);
        int totalScore;
        for (int index = 0; records.isReadyFrameScore(index); index++) {
            totalScore = records.getTotalScore(index);
            System.out.print(DOUBLE_BLANK + getTotalScoreString(totalScore) + TOTAL_SCORE_RIGHT_STRING);
        }
    }

    private static String getTotalScoreString(int totalScore) {
        if (totalScore < 10) {
            return "0" + totalScore;
        }
        return "" + totalScore;
    }

    private static void printPinScore(Records records) {
        for (Frame frame : records.getFrames()) {
            printScore(frame.getScores());
            printBonusScore(frame);
        }
    }

    private static void printBonusScore(Frame frame) {
        if (frame.isValidBonusGameScore()) {
            FinalFrame finalFrame = (FinalFrame) frame;
            printScore(finalFrame.getBonusScores());
        }
    }

    private static void printScore(Scores scores) {
        if (!scores.isValid()) {
            return;
        }

        FrameScore frameScore = FrameScore.from(scores);
        String printString = ScorePhrase.getScoreString(frameScore);
        List<String> downPins = getDownPinsString(scores);
        System.out.printf(printString, getOrDefault(downPins, 0, ""), getOrDefault(downPins, 1, ""));
    }

    private static String getOrDefault(List<String> list, int index, String defaultString) {
        if (list.size() > index) {
            return list.get(index);
        }
        return defaultString;
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
