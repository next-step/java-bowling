package bowling.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameResult {

    STRIKE("X", NormalFrame::scoreInStrike),
    SPARE("/", NormalFrame::scoreInSpare),
    MISS("", NormalFrame::scoreInMiss),
    GUTTER("-", NormalFrame::scoreInMiss);

    private static final int MAX_PIN_NUMBER = 10;

    private final String frameResult;
    private final Function<NormalFrame, Integer> function;

    FrameResult(String frameResult, Function<NormalFrame, Integer> function) {
        this.frameResult = frameResult;
        this.function = function;
    }

    public String frameResult() {
        return frameResult;
    }

    public static String eachResult(PinNumber pinNumber) {
        if (pinNumber.isStrike()) {
            return STRIKE.frameResult();
        }
        if (pinNumber.isGutter()) {
            return GUTTER.frameResult();
        }
        return Integer.toString(pinNumber.pinNumber());
    }

    public static String pairResult(PinNumber first, PinNumber second) {
        if (first.pinNumber() + second.pinNumber() == MAX_PIN_NUMBER) {
            return SPARE.frameResult();
        }
        if (second.isGutter()) {
            return GUTTER.frameResult();
        }
        return Integer.toString(second.pinNumber());
    }

    public static int scoreByResult(NormalFrame frame, String result) {
        return Arrays.stream(values())
                .filter(value -> value.frameResult.equals(result))
                .findFirst()
                .orElse(MISS)
                .function.apply(frame);
    }
}