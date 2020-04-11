package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputView {
    private static final String SCORE_BOARD_SUBJECT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_BOARD_CONTENTS = "|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|\n";

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
            String value = "";

            FrameRounds frameRounds = frame.getFrameRounds();
            RoundsStatus status = frameRounds.getStatus();

            if (status == RoundsStatus.STRIKE) {
                value += "X";
                values.add(value);

                continue;
            }

            for (FrameRound frameRound : frameRounds.getFrameRounds()) {
                if (frameRound.getRoundIndex() != 0) {
                    value += "|";
                }

                value += convertClearPinCount(frameRound.getClearPinCount());
            }
            values.add(value);
        }

        for (int i = 0, end = 10 - values.size(); i < end; i++) {
            values.add("");
        }

        return values;
    }

    private static String convertClearPinCount(int clearPinCount) {
        if (clearPinCount == 0) {
            return "-";
        }

        return String.valueOf(clearPinCount);
    }
}
