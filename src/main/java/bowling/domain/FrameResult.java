package bowling.domain;

import bowling.domain.exceptions.NoFrameStatusException;

import java.util.Arrays;

public enum FrameResult {
    STRIKE("X", new NumberOfHitPin(10)),
    NINE("9", new NumberOfHitPin(9)),
    EIGHT("8", new NumberOfHitPin(8)),
    SEVEN("7", new NumberOfHitPin(7)),
    SIX("6", new NumberOfHitPin(6)),
    FIVE("5", new NumberOfHitPin(5)),
    FOUR("4", new NumberOfHitPin(4)),
    THREE("3", new NumberOfHitPin(3)),
    TWO("2", new NumberOfHitPin(2)),
    ONE("1", new NumberOfHitPin(1)),
    GUTTER("-", new NumberOfHitPin(0)),
    SPARE("/", null);

    private String value;
    private NumberOfHitPin numberOfHitPin;

    FrameResult(String value, NumberOfHitPin numberOfHitPin) {
        this.value = value;
        this.numberOfHitPin = numberOfHitPin;
    }

    public static FrameResult find(NumberOfHitPin numberOfHitPin) {
        if (numberOfHitPin.equals(new NumberOfHitPin(10))) {
            return STRIKE;
        }
        if (numberOfHitPin.equals(new NumberOfHitPin(0))) {
            return GUTTER;
        }
        return Arrays.stream(FrameResult.values())
                .filter(frameStatus -> frameStatus.numberOfHitPin.equals(numberOfHitPin))
                .findFirst()
                .orElseThrow(() -> new NoFrameStatusException("잘못된 프레임 상태입니다."));
    }

    public static FrameResult find(NumberOfHitPin firstValue, NumberOfHitPin secondValue) {
        if (firstValue.plus(secondValue).equals(new NumberOfHitPin(10))) {
            return SPARE;
        }
        return FrameResult.find(secondValue);
    }
}
