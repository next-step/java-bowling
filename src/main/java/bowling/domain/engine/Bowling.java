package bowling.domain.engine;

import bowling.dto.BowlingDto;
import bowling.dto.ViewObject;
import bowling.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Bowling {

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

    private Frame goToNextFrame() {
        Frame nextFrame = frameCreator.create();
        frames.add(nextFrame);

        return nextFrame;
    }

    public BowlingDto export() {
        return frames.stream().map(ViewObject::export).collect(collectingAndThen(toList(), BowlingDto::new));
    }

}
