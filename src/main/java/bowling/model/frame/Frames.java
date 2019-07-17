package bowling.model.frame;

import bowling.model.Pin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int LAST_INDEX = 1;
    private List<Frame> frames;

    private Frames(List<Frame> frameList) {
        this.frames = new ArrayList<>(frameList);
    }

    public static Frames initialize() {
        return new Frames(Arrays.asList(Frame.initialize()));
    }

    // todo: No chaining
    public Frames saveBowling(Pin downPin) {
        Frame frameAfterBowling = currentFrame().bowl(downPin);

        if (isEqualFrame(frameAfterBowling)) {
            frames.set(getLastIndex(), frameAfterBowling);
            return this;
        }

        frames.add(frameAfterBowling);
        return this;
    }

    private Frame currentFrame() {
        return frames.get(getLastIndex());
    }

    private int getLastIndex() {
        return frames.size() - LAST_INDEX;
    }

    private boolean isEqualFrame(Frame frameAfterBowling) {
        return currentFrame().getNumber() == frameAfterBowling.getNumber();
    }

    public Results getResult() {
        return frames.stream()
                .map(Frame::getResult)
                .collect(collectingAndThen(toList(), Results::of));
    }

    public FrameNumber getCurrentNumber() {
        return currentFrame().getNumber();
    }

    public boolean isGameOver() {
        return currentFrame().isGameOver();
    }

    List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
