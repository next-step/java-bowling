package bowling.view;

import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameRound;
import bowling.domain.frame.Pin;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final int EACH_STRING_MAX_SIZE = 6;
    private static final String SEPARATOR = "|";
    private static final String BLANK = " ";

    public static void printScore(String player, List<Frame> frames) {
        List<String> result = new ArrayList<>();
        result.add(player);

        frames.stream()
                .filter(frame -> !frame.isFinal())
                .filter(frame -> frame.getPinsSize() > 0)
                .map(ResultView::getNormalFrame)
                .forEach(result::add);

        Frame finalFrame = frames.get(FrameRound.MAX_ROUND - 1);
        result.add(getFinalFrame(finalFrame));
        printResult(result);
    }

    public static void printResult(List<String> results) {
        System.out.println(HEADER);
        printGameResult(results);
    }

    private static void printGameResult(List<String> result) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 11)
                .forEach(i -> sb.append(SEPARATOR).append(getResult(result, i)));
        System.out.println(sb + SEPARATOR) ;
    }

    private static String getResult(List<String> results, int index) {
        if (results.size() <= index) {
            return getCenterFormat("", EACH_STRING_MAX_SIZE);
        }
        return getCenterFormat(results.get(index), EACH_STRING_MAX_SIZE);
    }

    public static String getCenterFormat(String str, int size) {
        if (str == null) {
            str = "";
        }
        return getCenterAligned(str, size);
    }

    private static String getCenterAligned(String str, int size) {
        StringBuilder sb = new StringBuilder(size);
        appendPad(sb, (size - str.length()) / 2);
        sb.append(str);
        appendPad(sb, (size - sb.length()));
        return sb.toString();
    }

    private static void appendPad(StringBuilder sb, int range) {
        IntStream.range(0, range)
                .forEach(index -> sb.append(BLANK));
    }

    private static String getFinalFrame(Frame frame) {
        if (!(frame.getPinsSize() > 0)) {
            return "";
        }
        return getFinal(frame);
    }

    private static String getFinal(Frame frame) {
        Pin firstPin = frame.getPin(Frame.FIRST_PITCH - 1);
        boolean isFirst = frame.getPinsSize() == Frame.FIRST_PITCH;
        boolean isSecond = frame.getPinsSize() == Frame.SECOND_PITCH;
        if (isFirst) {
            return getType(firstPin);
        }

        if (isSecond) {
            return getType(firstPin) + SEPARATOR +  nextType(frame);
        }

        return isBonusThenPin(frame);
    }

    private static String isBonusThenPin(Frame frame) {
        Pin firstPin = frame.getPin(Frame.FIRST_PITCH - 1);
        if (frame.getPinsSize() == Frame.BONUS_PITCH) {
            Pin bonus = frame.getPin(Frame.BONUS_PITCH - 1);
            return getType(firstPin) + SEPARATOR + nextType(frame) + SEPARATOR + getType(bonus);
        }

        return getType(firstPin) + SEPARATOR + nextType(frame);
    }

    private static String getNormalFrame(Frame frame) {
        Pin firstPin = frame.getPin(Frame.FIRST_PITCH - 1);
        if (frame.getPinsSize() == Frame.FIRST_PITCH) {
            return getType(firstPin);
        }

        return getType(firstPin) + SEPARATOR + nextType(frame);
    }

    private static String getType(Pin pin) {
        if (pin.getCount() == Pin.MAX_PIN_COUNT) {
            return Score.STRIKE.getType();
        }

        if (pin.getCount() == Pin.MIN_PIN_COUNT) {
            return Score.GUTTER.getType();
        }

        return pin.convertString();
    }

    private static String nextType(Frame frame) {
        Pin first = frame.getPin(Frame.FIRST_PITCH - 1);
        Pin second = frame.getPin(Frame.SECOND_PITCH - 1);

        if ((first.getCount() + second.getCount()) == Pin.MAX_PIN_COUNT) {
            return Score.SPARE.getType();
        }

        return getType(second);
    }
}
