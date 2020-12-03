package bowling.domain.frame;

import bowling.domain.Rolls;

interface FrameState {
    FrameEnum getFrameEnum();

    int getScore(Frame context, Rolls rolls);

    boolean hasScore(Frame context, Rolls rolls);

    void update(Frame context, Rolls rolls);
}
