package bowling.domain.frames;

import bowling.domain.Pins;

interface FramesState {
    void update(Frames frames, Pins pins);
}
