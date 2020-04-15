package bowling.view;

import bowling.domain.*;

import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final String SCORE_BOARD_NAME = "| NAME |";
    private static final String SCORE_BOARD_NAME_CONTENTS = "|  %s |";
    private static final String SCORE_BOARD_INIT = "|      |";
    private static final String ADD_SCORE_BOARD_SUBJECT = "   %02d   |";
    private static final String ADD_SCORE_BOARD_CONTENTS = "%8s|";
    private static final String STRIKE_SIGN = "X";
    private static final String SPARE_SIGN = "/";
    private static final String GUTTER_SIGN = "-";
    private static final String SEPARATOR = "|";
    private static final String BLANK = "";

    private static final int ZERO = 0;
    private static final int ONE = 1;

    private OutputView() {
    }

    public static void printScoreBoard(String playerName, Frames frames) {
        Map<Integer, ScoreView> scoreMap = makeScore(frames);

        String subject = SCORE_BOARD_NAME;
        String contents = String.format(SCORE_BOARD_NAME_CONTENTS, playerName);
        String scores = SCORE_BOARD_INIT;

        for (Map.Entry<Integer, ScoreView> scoreEntry : scoreMap.entrySet()) {
            ScoreView scoreView = scoreEntry.getValue();

            subject += String.format(ADD_SCORE_BOARD_SUBJECT, scoreEntry.getKey());
            contents += String.format(ADD_SCORE_BOARD_CONTENTS, scoreView.getSign());
            scores += String.format(ADD_SCORE_BOARD_CONTENTS, scoreView.getScore());
        }

        System.out.println(subject);
        System.out.println(contents);
        System.out.println(scores);
    }

    private static Map<Integer, ScoreView> makeScore(Frames frames) {
        Map<Integer, ScoreView> scoreMap = new HashMap<>();

        for (Frame frame : frames.getFrames()) {
            scoreMap.putAll(addScoreBoardValue(frame));
        }

        for (int i = scoreMap.size() + ONE, end = Frame.MAX_FRAME_NUMBER; i <= end; i++) {
            scoreMap.put(i, ScoreView.of(BLANK, BLANK));
        }

        return scoreMap;
    }

    private static Map<Integer, ScoreView> addScoreBoardValue(Frame frame) {
        Map<Integer, ScoreView> scoreMap = new HashMap<>();

        FrameRounds frameRounds = frame.getFrameRounds();
        ScoreStatus scoreStatus = frameRounds.getScoreStatus();

        String value = BLANK;

        for (FrameRound frameRound : frameRounds.getFrameRounds()) {
            value += getScourValue(frameRound.getRoundIndex(), scoreStatus.getStatus(), frameRound);
        }

        scoreMap.put(frame.getFrameIndex() + ONE, ScoreView.of(scoreStatus, value));

        return scoreMap;
    }

    private static String getScourValue(int roundIndex, RoundsStatus status, FrameRound frameRound) {
        String value = BLANK;

        if (frameRound.getRoundIndex() != ZERO) {
            value += SEPARATOR;
        }

        value += convertClearPinCount(roundIndex, status, frameRound.getClearPinCount());

        return value;
    }

    private static String convertClearPinCount(int roundIndex, RoundsStatus status, int clearPinCount) {
        if (clearPinCount == RoundsStatus.MAX_CLEAR_PIN_COUNT) {
            return STRIKE_SIGN;
        }

        if (roundIndex != ZERO && status.isSpare()) {
            return SPARE_SIGN;
        }

        if (clearPinCount == ZERO) {
            return GUTTER_SIGN;
        }

        return String.valueOf(clearPinCount);
    }
}
