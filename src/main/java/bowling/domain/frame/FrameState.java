package bowling.domain.frame;

import bowling.domain.Pins;

interface FrameState {
    FrameEnum getFrameEnum();

    int getScore(Frame context, Pins pins);

    boolean hasScore(Frame context, Pins pins);

    void update(Frame context, Pins pins);
}
