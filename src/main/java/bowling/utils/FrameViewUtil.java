package bowling.utils;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FrameViewUtil {

    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    private FrameViewUtil() {
    }

    public static List<String> show(final Frame frame) {
        if (frame.pinSize() == 3) {
            return showWhenSizeThree(frame);
        }
        if (frame.pinSize() == 2) {
            return showWhenSizeTwo(frame);
        }
        return showWhenSizeOne(frame);
    }

    private static List<String> showWhenSizeThree(final Frame frame) {
        Pin firstPin = frame.firstPin();
        Pin secondPin = frame.secondPin();
        Pin thirdPin = frame.thirdPin();

        return Arrays.asList(
                stringOfFirstPin(firstPin),
                stringOfSecondPin(firstPin, secondPin),
                stringOfThirdPin(firstPin, secondPin, thirdPin)
        );
    }

    private static List<String> showWhenSizeTwo(final Frame frame) {
        Pin firstPin = frame.firstPin();
        Pin secondPin = frame.secondPin();
        return Arrays.asList(stringOfFirstPin(firstPin), stringOfSecondPin(firstPin, secondPin));
    }

    private static List<String> showWhenSizeOne(final Frame frame) {
        Pin firstPin = frame.firstPin();
        return Collections.singletonList(stringOfFirstPin(firstPin));
    }

    private static String stringOfFirstPin(final Pin firstPin) {
        if (firstPin.isMinimum()) {
            return GUTTER;
        }

        if (firstPin.isMaximum()) {
            return STRIKE;
        }
        return firstPin.toString();
    }

    private static String stringOfSecondPin(final Pin firstPin, final Pin secondPin) {
        if (firstPin.isMaximum() && secondPin.isMaximum()) {
            return STRIKE;
        }
        if (secondPin.isMinimum()) {
            return GUTTER;
        }
        if (firstPin.getKnockDownNumber() + secondPin.getKnockDownNumber() == Pin.MAX) {
            return SPARE;
        }
        return secondPin.toString();
    }

    private static String stringOfThirdPin(final Pin firstPin, final Pin secondPin, final Pin thirdPin) {
        if (thirdPin.isMinimum()) {
            return GUTTER;
        }
        if (firstPin.isMaximum() && secondPin.getKnockDownNumber() + thirdPin.getKnockDownNumber() == Pin.MAX) {
            return SPARE;
        }
        if (thirdPin.isMaximum()) {
            return STRIKE;
        }
        return thirdPin.toString();
    }
}