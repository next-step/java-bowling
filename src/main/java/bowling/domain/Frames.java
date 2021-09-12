package bowling.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Frames {

    private static final int SIZE = 10;

    private final Stack<Frame> frames;
    private final List<Integer> scores;

    public Frames() {
        frames = new Stack<>();
        frames.add(new NormalFrame());
        scores = new ArrayList<>();
    }

    public boolean addPitch(final Pitch pitch) {
        if (currentFrame().addPitchIfPossible(pitch)) {
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

    public List<Integer> scores() {
        IntStream.range(scores.size(), frames.size())
                .forEach(this::addScoreIfExist);
        return Stream.of(scores, emptyIntList())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> emptyIntList() {
        return Stream.generate(() -> 0)
                .limit(SIZE - scores.size())
                .collect(Collectors.toList());
    }

    private void addScoreIfExist(final int index) {
        int score = frames.get(index)
                .score();
        if (score > 0) {
            scores.add(score + getPrevFrameScore());
        }
    }

    private int getPrevFrameScore() {
        return scores.isEmpty()
                ? 0
                : scores.get(scores.size() - 1);
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
        Frame nextFrame = nextFrame();
        currentFrame().addNextFrame(nextFrame);
        frames.add(nextFrame);
    }

    private Frame nextFrame() {
        if (frames.size() < SIZE - 1) {
            return new NormalFrame();
        }
        return new FinalFrame();
    }

}
