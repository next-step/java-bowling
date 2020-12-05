package bowling.domain.frame;

import bowling.domain.Pins;

interface FrameState {
    FrameEnum getFrameEnum();

    int getScore(Frame frame, Pins pins);

    boolean hasScore(Frame frame, Pins pins);

    void update(Frame frame, Pins pins);
}
