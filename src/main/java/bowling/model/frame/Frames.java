package bowling.model.frame;

import bowling.model.Pins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {

    private List<Frame> frames;

    private Frames(List<Frame> frameList) {
        this.frames = new ArrayList<>(frameList);
    }

    public static Frames initialize() {
        return new Frames(Arrays.asList(Frame.initialize()));
    }

    public Frames saveBowling(Pins downPins) {
        Frame frameAfterBowling = currentFrame().bowl(downPins);

        if (isEqualFrame(frameAfterBowling)) {
            frames.set(getLastIndex(), frameAfterBowling);
        }

        if (!isEqualFrame(frameAfterBowling)) {
            frames.add(frameAfterBowling);
        }
        return this;
    }

    private Frame currentFrame() {
        return frames.get(getLastIndex());
    }

    private int getLastIndex() {
        return frames.size() - 1;
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
