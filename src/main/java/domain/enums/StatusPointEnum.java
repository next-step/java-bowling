package domain.enums;

import java.util.Arrays;

public enum StatusPointEnum {
    STRIKE(0, StatusFrameEnum.FIRST),
    SPARE(0, StatusFrameEnum.SECOND),
    MISS(0, StatusFrameEnum.SECOND),
    GUTTER(10, StatusFrameEnum.SECOND);

    private int pinCount;
    private StatusFrameEnum statusFrameEnum;

    StatusPointEnum(int pinCount, StatusFrameEnum frameStatus) {
        this.pinCount = pinCount;
        this.statusFrameEnum = frameStatus;
    }

    public static StatusPointEnum Of(int pinCount, StatusFrameEnum statusFrameEnum) {
        return Arrays.stream(StatusPointEnum.values())
                .filter(statusPointEnum -> statusPointEnum.equals(pinCount, statusFrameEnum))
                .findAny()
                .orElse(MISS);
    }

    private boolean equals(int pinCount, StatusFrameEnum statusFrameEnum) {
        if (this.pinCount == pinCount && this.statusFrameEnum == statusFrameEnum) {
            return true;
        }
        return false;
    }
}
