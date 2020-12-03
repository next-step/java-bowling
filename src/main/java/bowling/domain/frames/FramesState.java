package bowling.domain.frames;

import bowling.domain.Rolls;

interface FramesState {
    void update(Frames context, Rolls rolls);
}
