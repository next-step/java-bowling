package bowling.domain.frames;

import bowling.domain.Rolls;
import bowling.domain.frame.Frame;
import bowling.dto.FramesDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {
    private final LinkedList<Frame> frames = new LinkedList<>();
    private FramesState state = EmptyFramesState.getInstance();

    void setState(FramesState state) {
        this.state = state;
    }

    void addFrame(Frame frame) {
        frames.add(frame);
    }

    public List<Frame> subList(int from, int to) {
        return frames.subList(from, to);
    }

    public int size() {
        return frames.size();
    }

    Frame getLast() {
        return frames.getLast();
    }

    public void update(Rolls rolls) {
        state.update(this, rolls);
    }

    public FramesDto exportFramesDto() {
        return frames.stream()
                .map(Frame::exportFrameDto)
                .collect(collectingAndThen(toList(), FramesDto::new));
    }
}
