package bowling.view;

import bowling.domain.*;
import bowling.exception.NotReadyException;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static String INIT_SCORE_BOARD_STRING = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String NAME_STRING = "|  %s |";
    private static String GUTTER_STRING = "-";
    private static String TOTAL_SCORE_STRING = "\n|      |";
    private static String TOTAL_SCORE_RIGHT_STRING = "  |";
    private static String DOULBE_BLANK = "  ";

    public static void printScoreBoard(BowlingGame game) {
        System.out.println(INIT_SCORE_BOARD_STRING);
        System.out.printf(NAME_STRING, game.getPlayer().getName());
        printPinScore(game.getRecords());
        try {
            printTotalScores(game.getRecords());
        } catch (NotReadyException ignored) {
        }
        System.out.println();
    }

    private static void printTotalScores(Records records) {
        System.out.print(TOTAL_SCORE_STRING);
        int totalScore;
        for (int index = 0; index < records.getRecordCount(); index++) {
            totalScore = records.getTotalScore(index);
            System.out.print(DOULBE_BLANK + getTotalScoreString(totalScore) + TOTAL_SCORE_RIGHT_STRING);
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
        if (!frame.getClass().equals(FinalFrame.class)) {
            return;
        }

        FinalFrame finalFrame = (FinalFrame) frame;
        if (finalFrame.isValidBonusScore()) {
            printScore(finalFrame.getBonusScores());
        }
    }

    private static void printScore(Scores scores) {
        if (!scores.isValid()) {
            return;
        }

        FrameScore frameScore = FrameScore.from(scores);
        String printString = ScoreString.getScoreString(frameScore);
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
