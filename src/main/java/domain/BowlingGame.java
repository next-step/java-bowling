package domain;

import domain.frame.Frame;
import domain.frame.FrameIndex;
import domain.frame.LastFrame;
import domain.frame.NormalFrame;
import domain.state.State;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.frame.FrameIndex.LAST_NORMAL_FRAME;
import static domain.frame.FrameIndex.START_FRAME;

public class BowlingGame {

    private List<Frame> frames;
    private FrameIndex currentFrameIndex;

    public BowlingGame() {
        this.currentFrameIndex = FrameIndex.of(START_FRAME);
        this.frames = IntStream.range(START_FRAME, LAST_NORMAL_FRAME)
                .mapToObj(index -> new NormalFrame())
                .collect(Collectors.toList());
        this.frames.add(new LastFrame());
    }

    public List<Frame> bowl(Pins downPins) {
        Frame frame = getCurrentFrame();
        State state = frame.setKnockedDownPins(downPins);
        if (state.isClosed()) {
            currentFrameIndex = currentFrameIndex.next();
        }
        return frames;
    }

    public FrameIndex getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public boolean isGameFinished() {
        return currentFrameIndex.isStop();
    }

    private Frame getCurrentFrame() {
        return frames.get(currentFrameIndex.value());
    }

}
