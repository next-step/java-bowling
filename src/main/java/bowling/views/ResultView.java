package bowling.views;

import bowling.domain.*;
import bowling.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private final static String STRIKE = "X";
    private final static String SPARE = "/";
    private final static String GUTTER = "-";
    private final static String SEPARATOR = "|";
    private final static int GUTTER_PIN_COUNT = 0;

    private final static int FIRST_ROUND = 0;
    private final static int FINAL_ROUND = 10;
    private final static int FIRST_STATE = 1;
    private final static int FINAL_STATE = 3;

    private final static int FIRST_INDEX = 0;
    private final static int FRAME_WIDTH = 6;
    private final static int FRAME_WIDTH_HALF = 2;

    private final static String SCORE_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private final static String BLANK = "    ";
    private final static String ENTER = System.lineSeparator();

    private ResultView() {}

    private static String getStateOrName(String result) {
        int rightPadding = (FRAME_WIDTH - result.length()) / FRAME_WIDTH_HALF;
        int leftPadding = FRAME_WIDTH - result.length() - rightPadding;
        return SEPARATOR +
                StringUtils.generateBlank(leftPadding) +
                result +
                StringUtils.generateBlank(rightPadding);
    }

    private static String getBlankFrameStates() {
        StringBuilder result = new StringBuilder();
        for (int i = ResultView.FIRST_ROUND; i < FINAL_ROUND; i++) {
            result.append(getStateOrName(BLANK));
        }
        return result.toString();
    }

    private static String getBlankOrFrameState(Frame frame) {
        if (frame.hasResult()) {
            return getFrameStates(frame.states(), frame.downPins());
        }
        return getStateOrName(BLANK);
    }

    private static String getFrameStates(List<State> states, DownPins downPins) {
        if (states.size() == FIRST_STATE) {
            return getFrameState(states.get(FIRST_INDEX), downPins);
        }
        String value = getFinalFrameStates(states, downPins);
        return getStateOrName(value);
    }

    private static String getFinalFrameStates(List<State> states, DownPins downPins) {
        return IntStream.range(FIRST_INDEX, states.size())
                .mapToObj(i -> State.STRIKE.equals(states.get(i))
                        ? STRIKE
                        : getSpareOrGutter(downPins.count(i)))
                .collect(Collectors.joining(SEPARATOR));
    }

    private static String getFrameState(State state, DownPins downPins) {
        if (State.STRIKE.equals(state)) {
            return getStateOrName(STRIKE);
        }
        if (State.SPARE.equals(state)) {
            String value = getSpareOrGutter(downPins.count(FIRST_INDEX)) + SEPARATOR + SPARE;
            return getStateOrName(value);
        }
        return getStateOrName(pinResult(downPins));
    }

    private static String pinResult(DownPins downPins) {
        return downPins.downPins()
                .stream()
                .map(DownPin::count)
                .map(ResultView::getSpareOrGutter)
                .collect(Collectors.joining(SEPARATOR));
    }

    private static String getSpareOrGutter(int pinCount) {
        if (pinCount == GUTTER_PIN_COUNT) {
            return GUTTER;
        }
        return String.valueOf(pinCount);
    }

    public static String frameResult(Player player, Frames frames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStateOrName(player.name()));
        for (Frame frame: frames.frames()) {
            stringBuilder.append(getBlankOrFrameState(frame));
        }
        return stringBuilder + SEPARATOR + ENTER;
    }

    public static void showScoreBoard(Player player, Frames frames) {
        System.out.println(SCORE_TITLE +
                ENTER +
                frameResult(player, frames));
    }

    public static void showScoreBoard(Player player) {
        String stringBuilder = SCORE_TITLE +
                ENTER +
                getStateOrName(player.name()) +
                getBlankFrameStates() +
                SEPARATOR +
                ENTER;
        System.out.println(stringBuilder);
    }
}
