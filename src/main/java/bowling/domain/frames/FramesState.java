package bowling.domain.frames;

import bowling.domain.Rolls;

interface FramesState {
    void update(FramesContext context, Rolls rolls);
}
