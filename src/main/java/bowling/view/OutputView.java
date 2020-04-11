package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputView {
    private static final String SCORE_BOARD_SUBJECT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_BOARD_CONTENTS = "|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|\n";
    private static final String STRIKE_SIGN = "X";
    private static final String GUTTER_SIGN = "-";
    private static final String SEPARATOR = "|";

    private static final int ZERO = 0;
    private static final int MAX_FAME_COUNT = 10;

    public static void printScoreBoard(String playerName, Frames frames) {
        List<String> values = new ArrayList<>();
        values.add(playerName);
        values.addAll(makeScore(frames));

        System.out.println(SCORE_BOARD_SUBJECT);
        System.out.printf(SCORE_BOARD_CONTENTS, Arrays.asList(values.stream().toArray(String[]::new)).toArray());
    }

    private static List<String> makeScore(Frames frames) {
        List<String> values = new ArrayList<>();

        for (Frame frame : frames.getFrames()) {
            addScoreBoardValue(frame, values);
        }

        for (int i = ZERO, end = MAX_FAME_COUNT - values.size(); i < end; i++) {
            values.add("");
        }

        return values;
    }

    private static void addScoreBoardValue(Frame frame, List<String> values) {
        FrameRounds frameRounds = frame.getFrameRounds();
        RoundsStatus status = frameRounds.getStatus();

        String value = "";

        if (status == RoundsStatus.STRIKE) {
            value += STRIKE_SIGN;
            values.add(value);

            return;
        }

        for (FrameRound frameRound : frameRounds.getFrameRounds()) {
            value += getScourValue(frameRound);
        }
        values.add(value);
    }

    private static String getScourValue(FrameRound frameRound) {
        String value = "";
        if (frameRound.getRoundIndex() != ZERO) {
            value += SEPARATOR;
        }

        value += convertClearPinCount(frameRound.getClearPinCount());

        return value;
    }

    private static String convertClearPinCount(int clearPinCount) {
        if (clearPinCount == ZERO) {
            return GUTTER_SIGN;
        }

        return String.valueOf(clearPinCount);
    }
}
