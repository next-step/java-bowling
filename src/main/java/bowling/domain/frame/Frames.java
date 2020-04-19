package bowling.domain.frame;

import bowling.domain.state.PinCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.Constants.FIRST_FRAME_NUMBER;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.create(new FrameNumber(FIRST_FRAME_NUMBER)));
    }

    public FrameResults play(PinCount felledPin) {
        Frame frame = getCurrentFrame();
        frame.play(felledPin);

        FrameResults frameResults = new FrameResults(new ArrayList<>(frames));

        if (!frame.isLastFrame() && frame.isEndedFrame()) {
            Frame nextFrame = frame.getNext();

            if(Objects.nonNull(nextFrame)) {
                addFrame(nextFrame);
            }
        }

        return frameResults;
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private void addFrame(Frame frame) {
        if (Objects.nonNull(frame)) {
            frames.add(frame);
        }
    }

    public boolean isEnd() {
        return getCurrentFrame().isLastFrame();
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    public FrameResults getCurrentFrameResults() {
        return new FrameResults(new ArrayList<>(frames));
    }

    public List<Frame> getValue() {
        return new ArrayList<>(frames);
    }
}
