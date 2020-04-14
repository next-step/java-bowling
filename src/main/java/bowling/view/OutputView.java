package bowling.view;

import bowling.domain.*;

import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final String SCORE_BOARD_NAME = "| NAME |";
    private static final String SCORE_BOARD_NAME_CONTENTS = "|  %s |";
    private static final String ADD_SCORE_BOARD_SUBJECT = "   %02d   |";
    private static final String ADD_SCORE_BOARD_CONTENTS = "%8s|";
    private static final String STRIKE_SIGN = "X";
    private static final String GUTTER_SIGN = "-";
    private static final String SEPARATOR = "|";
    private static final String BLANK = "";

    private static final int ZERO = 0;
    private static final int ONE = 1;

    private OutputView() {
    }

    public static void printScoreBoard(String playerName, Frames frames) {

        Map<Integer, String> scoreMap = makeScore(frames);

        String subject = SCORE_BOARD_NAME;
        String contents = String.format(SCORE_BOARD_NAME_CONTENTS, playerName);

        for (Map.Entry<Integer, String> scoreEntry : scoreMap.entrySet()) {
            subject += String.format(ADD_SCORE_BOARD_SUBJECT, scoreEntry.getKey());
            contents += String.format(ADD_SCORE_BOARD_CONTENTS, scoreEntry.getValue());
        }

        System.out.println(subject);
        System.out.println(contents);
    }

    private static Map<Integer, String> makeScore(Frames frames) {
        Map<Integer, String> scoreMap = new HashMap<>();

        for (Frame frame : frames.getFrames()) {
            scoreMap.putAll(addScoreBoardValue(frame));
        }

        for (int i = scoreMap.size() + ONE, end = Frame.MAX_FRAME_NUMBER; i <= end; i++) {
            scoreMap.put(i, BLANK);
        }

        return scoreMap;
    }

    private static Map<Integer, String> addScoreBoardValue(Frame frame) {
        Map<Integer, String> scoreMap = new HashMap<>();

        FrameRounds frameRounds = frame.getFrameRounds();

        String value = BLANK;

        for (FrameRound frameRound : frameRounds.getFrameRounds()) {
            value += getScourValue(frameRound.getRoundIndex(), frameRounds.getStatus(), frameRound);
        }

        scoreMap.put(frame.getFrameIndex() + ONE, value);

        return scoreMap;
    }

    private static String getScourValue(int roundIndex, RoundsStatus status, FrameRound frameRound) {
        if (roundIndex == ZERO && RoundsStatus.isStrike(status)) {
            return STRIKE_SIGN;
        }

        String value = BLANK;

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
