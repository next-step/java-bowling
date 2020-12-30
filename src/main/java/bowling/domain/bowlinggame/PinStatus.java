package bowling.domain.bowlinggame;

import bowling.domain.frame.DownedPin;
import bowling.domain.frame.FrameStatus;

import java.util.List;

public enum PinStatus {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private final String mark;

    PinStatus(String mark) {
        this.mark = mark;
    }

    public static String interpretPinsStatus(List<DownedPin> pins) {
        FrameStatus frameStatus = FrameStatus.decideStatus(pins);

        if (frameStatus == FrameStatus.ON_FRAME) {
            return " " + changeMark(pins.get(0)) + " ";
        }

        if (frameStatus == FrameStatus.MISS) {
            return " " + changeMark(pins.get(0)) + " | " + changeMark(pins.get(1)) + " ";
        }

        if (frameStatus == FrameStatus.STRIKE) {
            return " " + changeMark(pins.get(0)) + " ";
        }

        if (frameStatus == FrameStatus.SPARE) {
            return " " + changeMark(pins.get(0)) + " | " + SPARE.mark + " ";
        }

        return "";
    }

    private static String changeMark(DownedPin pin) {
        if (pin.isStrike()) {
            return STRIKE.mark;
        }

        if (pin.isGutter()) {
            return GUTTER.mark;
        }

        return Integer.toString(pin.getNumDownedPin());
    }
}
