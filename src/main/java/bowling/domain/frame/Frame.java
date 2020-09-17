package bowling.domain.frame;

import bowling.domain.pin.Pin;

public interface Frame {

    static Frame normalFrame() {
        return NormalFrame.of();
    }

    static Frame endFrame() {
        return EndFrame.of();
    }

    void bowl(Pin felledPin);

    boolean isEnd();
}
