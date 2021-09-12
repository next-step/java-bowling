package bowling.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {

    private static final int SIZE = 10;

    private final Stack<Frame> frames;

    public Frames() {
        frames = new Stack<>();
        frames.add(new NormalFrame());
    }

    public boolean addPitch(final Pitch pitch) {
        if (currentFrame().add(pitch)) {
            return true;
        }
        if (frames.size() == SIZE) {
            return false;
        }
        addNextFrame();
        return addPitch(pitch);
    }

    public List<List<Integer>> allFramePitchValues() {
        return Stream.of(framePitchValues(), emptyLists())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public int currentFrameNumber() {
        return currentFrame().isFull()
                ? frames.size() + 1
                : frames.size();
    }

    public boolean isEnd() {
        return currentFrameNumber() > Frames.SIZE;
    }

    private List<List<Integer>> framePitchValues() {
        return frames.stream()
                .map(Frame::pitchValues)
                .collect(Collectors.toList());
    }

    private List<List<Integer>> emptyLists() {
        return Stream.generate(ArrayList<Integer>::new)
                .limit(SIZE - frames.size())
                .collect(Collectors.toList());
    }

    private Frame currentFrame() {
        return frames.peek();
    }

    private void addNextFrame() {
        frames.add(nextFrame());
    }

    private Frame nextFrame() {
        if (frames.size() < SIZE - 1) {
            return new NormalFrame();
        }
        return new FinalFrame();
    }

}
