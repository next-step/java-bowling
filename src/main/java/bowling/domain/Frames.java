package bowling.domain;

import bowling.domain.frames.FramesContext;
import bowling.dto.FramesDto;

import java.util.List;

class Frames {
    private FramesContext context = new FramesContext();

    List<Frame> subList(int from, int to) {
        return context.subList(from, to);
    }

    int size() {
        return context.size();
    }

    void update(Rolls rolls) {
        context.update(rolls);
    }

    FramesDto exportFramesDto() {
        return context.exportFramesDto();
    }
}
