package domain.enums;


import domain.Pin;

import java.util.Arrays;

public enum StatusPointEnum {
    STRIKE(0, StatusFrameEnum.FIRST),
    SPARE(0, StatusFrameEnum.SECOND),
    MISS(0, StatusFrameEnum.SECOND),
    FIRSTGUTTER(10, StatusFrameEnum.FIRST),
    SECONDGUTTER(10, StatusFrameEnum.SECOND);

    private int pinCount;
    private StatusFrameEnum statusFrameEnum;

    StatusPointEnum(int pinCount, StatusFrameEnum frameStatus) {
        this.pinCount = pinCount;
        this.statusFrameEnum = frameStatus;
    }

    public static StatusPointEnum Of(Pin pin, StatusFrameEnum statusFrameEnum) {
        if (StatusFrameEnum.SECOND == statusFrameEnum && pin.isBallCountZero()) {
            return StatusPointEnum.SECONDGUTTER;
        }

        return Arrays.stream(StatusPointEnum.values())
                .filter(statusPointEnum -> statusPointEnum.equals(pin, statusFrameEnum))
                .findAny()
                .orElse(MISS);
    }

    private boolean equals(Pin pin, StatusFrameEnum statusFrameEnum) {
        if (pin.isSamePinCount(pinCount) && this.statusFrameEnum == statusFrameEnum) {
            return true;
        }
        return false;
    }
}
