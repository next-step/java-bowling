package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

public interface Pins {

    Pin firstPin();

    Pin secondPin();

    FrameStatus frameStatus();
}
