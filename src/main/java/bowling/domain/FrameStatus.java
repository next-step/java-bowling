package bowling.domain;

import bowling.domain.exceptions.NoFrameStatusException;

import java.util.Arrays;

public enum FrameStatus {
    STRIKE("X"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1"),
    GUTTER("-"),
    SPARE("/");

    private String value;

    FrameStatus(String value) {
        this.value = value;
    }

    public static FrameStatus find(Integer inputValue) {
        if (inputValue == 10) {
            return STRIKE;
        }
        if (inputValue == 0) {
            return GUTTER;
        }
        return Arrays.stream(FrameStatus.values())
                .filter(frameStatus -> frameStatus.value.equals(inputValue.toString()))
                .findFirst()
                .orElseThrow(() -> new NoFrameStatusException("잘못된 프레임 상태입니다."));
    }
}
