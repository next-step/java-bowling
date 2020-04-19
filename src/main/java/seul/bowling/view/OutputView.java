package seul.bowling.view;

import seul.bowling.domain.Frame;
import seul.bowling.domain.Frames;
import seul.bowling.domain.Pins;
import seul.bowling.domain.pin.Pin;
import seul.bowling.domain.status.Status;

import java.util.HashMap;
import java.util.List;
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
    private static final int MAX_CLEAR_PIN_COUNT = 10;

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
            Status status = frame.getStatus();
            scoreMap.putAll(addScoreBoardValue(frame.getIndex() + ONE, status, status.getPins()));
        }

        for (int i = scoreMap.size(), end = Frame.LAST_FRAME_INDEX; i <= end; i++) {
            scoreMap.put(i + ONE, ScoreView.of(BLANK, BLANK));
        }

        return scoreMap;
    }

    private static Map<Integer, ScoreView> addScoreBoardValue(int frameIndex, Status status, Pins pins) {
        Map<Integer, ScoreView> scoreMap = new HashMap<>();
        String scoreValue = getScourValue(status, pins.getPins());

        ScoreView scoreView = ScoreView.of(status.endScore(), status.getToTalScore(), scoreValue);
        scoreMap.put(frameIndex, scoreView);
        return scoreMap;
    }

    private static String getScourValue(Status status, List<Pin> pins) {
        String value = BLANK;

        for (int i = 0, end = pins.size(); i < end; i++) {
            Pin pin = pins.get(i);
            if (i != ZERO) {
                value += SEPARATOR;
            }

            value += convertClearPinCount(i, status, pin.getPin());
        }

        return value;
    }

    private static String convertClearPinCount(int index, Status status, int clearPinCount) {
        if (clearPinCount == MAX_CLEAR_PIN_COUNT) {
            return STRIKE_SIGN;
        }

        if (index != ZERO && status.isSpare()) {
            return SPARE_SIGN;
        }

        if (clearPinCount == ZERO) {
            return GUTTER_SIGN;
        }

        return String.valueOf(clearPinCount);
    }
}
