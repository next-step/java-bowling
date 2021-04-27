package bowling.domain.engine.frame;

import bowling.domain.RollResult;
import bowling.dto.FramesDto;

import java.util.LinkedList;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int TOTAL_FRAMES = 10;

    private final FrameCreator frameCreator = new FrameCreator();
    private final LinkedList<Frame> frames = new LinkedList<>();

    public Frames() {
        frames.add(frameCreator.create());
    }

    public void roll(RollResult rollResult) {
        Frame currentFrame = frames.getLast();
        if (currentFrame.isEnded()) {
            currentFrame = frameCreator.create();
            frames.add(currentFrame);
        }

        currentFrame.roll(rollResult);
    }

    public int getNextFrameNumber() {
        if (frames.getLast().isEnded()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public boolean isAllFrameEnded() {
        return frames.size() == TOTAL_FRAMES && frames.getLast().isEnded();
    }

    public FramesDto export() {
        return frames.stream()
                     .map(Frame::export)
                     .collect(collectingAndThen(toList(), FramesDto::new));
    }

}
