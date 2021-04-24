package bowling.domain.engine;

import bowling.dto.BowlingDto;
import bowling.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Bowling {

    private static final int TOTAL_FRAMES = 10;

    private final FrameCreator frameCreator = new FrameCreator();
    private final List<Frame> frames = new ArrayList<>();

    public Bowling() {
        frames.add(frameCreator.create());
    }

    public void throwBall(PitchResult pitchResult) {
        Frame currentFrame = ListUtils.getLastElement(frames);
        if (currentFrame.isEnded()) {
            currentFrame = goToNextFrame();
        }

        currentFrame.throwBall(pitchResult);
    }

    public int currentFrame() {
        if (ListUtils.getLastElement(frames).isEnded()) {
            return frames.size() + 1;
        } else {
            return frames.size();
        }
    }

    private Frame goToNextFrame() {
        Frame nextFrame = frameCreator.create();
        frames.add(nextFrame);

        return nextFrame;
    }

    public boolean isEnded() {
        return frames.size() == TOTAL_FRAMES && ListUtils.getLastElement(frames).isEnded();
    }

    public BowlingDto export() {
        return frames.stream().map(Frame::export).collect(collectingAndThen(toList(), BowlingDto::new));
    }

}
