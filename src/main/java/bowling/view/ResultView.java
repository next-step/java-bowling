package bowling.view;

import bowling.domain.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.util.StringUtils.getCenterFormat;
import static bowling.domain.Frame.FIRST_PITCH_COUNT;
import static bowling.domain.Frame.SECOND_PITCH_COUNT;
import static bowling.domain.Frame.BONUS_PITCH_COUNT;
import static bowling.domain.Frame.FRAME_DELIMITER;

public class ResultView {

    private static final int MAX_VIEW_FRAME_SIZE = 11;
    private static final int MAX_STRING_SIZE = 6;
    private static final String FIRST_COLUMN_NAME = "NAME";
    private static final String SCOREBOARD_SPLIT_SEPARATOR = "|";
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";

    private static final String HEADER;

    static {
        HEADER = makeHeader();
    }

    private ResultView() {}

    private static String makeHeader() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, MAX_VIEW_FRAME_SIZE)
                .mapToObj(String::valueOf)
                .forEach(i -> sb.append(SCOREBOARD_SPLIT_SEPARATOR).append(getHeader(i)));
        return sb.toString() + SCOREBOARD_SPLIT_SEPARATOR;
    }

    private static String getHeader(String value) {
        if (Integer.parseInt(value) == 0) {
            return getCenterFormat(FIRST_COLUMN_NAME, MAX_STRING_SIZE);
        }

        String number = String.format("%02d", Integer.parseInt(value));
        return getCenterFormat(number, MAX_STRING_SIZE);
    }

    public static void printHeader(List<Player> players) {
        System.out.println(HEADER);
        for (Player player : players) {
            List<String> results = new LinkedList<>();
            results.add(player.getName());
            printResult(results);
        }
    }

    public static void printScoreboard(List<String> results) {
        System.out.println(HEADER);
        printResult(results);
    }

    private static void printResult(List<String> results) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, MAX_VIEW_FRAME_SIZE)
                .forEach(i -> sb.append(SCOREBOARD_SPLIT_SEPARATOR).append(getResult(results, i)));
        System.out.println(sb.toString() + SCOREBOARD_SPLIT_SEPARATOR) ;
    }

    private static String getResult(List<String> results, int index) {
        if (results.size() <= index) {
            return getCenterFormat("", MAX_STRING_SIZE);
        }
        return getCenterFormat(results.get(index), MAX_STRING_SIZE);
    }

    public static void printPlayerScoreboard(String player, List<Frame> frames) {
        List<String> result = new LinkedList<>();
        result.add(player);
        frames.stream()
                .filter(frame -> !frame.isFinalFrame())
                .filter(frame -> frame.getPitchCount() > 0)
                .map(ResultView::getNormalFrameState)
                .forEach(result::add);

        Frame finalFrame = frames.get(FrameNumber.FRAME_LAST_NUMBER - 1);
        result.add(getFinalFrameState(finalFrame));
        printScoreboard(result);
    }

    private static String getNormalFrameState(Frame frame) {
        Pin firstPin = frame.getPin((FIRST_PITCH_COUNT - 1));
        if (frame.getPitchCount() == FIRST_PITCH_COUNT) {
            return getState(firstPin);
        }

        return getState(firstPin) + FRAME_DELIMITER + getGutter(frame);
    }

    private static String getFinalFrameState(Frame frame) {
        if (!(frame.getPitchCount() > 0)) {
            return "";
        }

        return getFinalFrame(frame);
    }

    private static String getFinalFrame(Frame frame) {
        Pin firstPin = frame.getPin((FIRST_PITCH_COUNT - 1));
        if (frame.getPitchCount() == FIRST_PITCH_COUNT) {
            return getState(firstPin);
        }

        if (frame.getPitchCount() == SECOND_PITCH_COUNT) {
            return getState(firstPin) + FRAME_DELIMITER + getGutter(frame);
        }

        return checkBonusPin(frame);
    }

    private static String checkBonusPin(Frame frame) {
        Pin firstPin = frame.getPin((FIRST_PITCH_COUNT - 1));
        if (frame.getPitchCount() == BONUS_PITCH_COUNT) {
            Pin bonusPin = frame.getPin((BONUS_PITCH_COUNT - 1));
            return getState(firstPin) + FRAME_DELIMITER + getGutter(frame) + FRAME_DELIMITER + getState(bonusPin);
        }

        return getState(firstPin) + FRAME_DELIMITER + getGutter(frame);
    }


    private static String getState(Pin pin) {
        if (pin.getPinCount() == Pin.MAX_PIN_COUNT) {
            return STRIKE;
        }

        if (pin.getPinCount() == Pin.MIN_PIN_COUNT) {
            return GUTTER;
        }

        return pin.getCountToString();
    }

    private static String getGutter(Frame frame) {
        Pin firstPin = frame.getPin((FIRST_PITCH_COUNT - 1));
        Pin secondPin = frame.getPin((SECOND_PITCH_COUNT - 1));

        if ((firstPin.getPinCount() + secondPin.getPinCount()) == Pin.MAX_PIN_COUNT) {
            return SPARE;
        }

        return getState(secondPin);
    }
}
