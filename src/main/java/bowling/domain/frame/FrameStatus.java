package bowling.domain.frame;

import bowling.domain.pin.Pin;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS,
    GUTTER,
    NONE;

    public static FrameStatus of(Pin firstPin, Pin secondPin) {
        return null;
    }
}
