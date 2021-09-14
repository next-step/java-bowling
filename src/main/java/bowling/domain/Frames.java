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
    private final List<Score> scores;

    public Frames() {
        frames = new Stack<>();
        frames.add(new NormalFrame());
        scores = new ArrayList<>();
    }

    public void addPitch(final Pitch pitch) {
        if (isEnd() || currentFrame().addPitchIfPossible(pitch)) {
            return;
        }
        addNextFrame();
        addPitch(pitch);
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

    public boolean isCurrentFrameEnd() {
        return currentFrame().isFull();
    }


    public List<String> scores() {
        IntStream.range(scores.size(), frames.size())
                .forEach(this::addScoreIfExist);
        return Stream.of(toScores(), emptyList())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> toScores() {
        return scores.stream()
                .map(Score::score)
                .collect(Collectors.toList());
    }

    private List<String> emptyList() {
        return Stream.generate(Score::noScore)
                .limit(SIZE - scores.size())
                .map(Score::score)
                .collect(Collectors.toList());
    }

    private void addScoreIfExist(final int index) {
        Score score = frames.get(index)
                .score();
        if (!score.isEmpty()) {
            scores.add(score.plus(prevFrameScore()));
        }
    }

    private Score prevFrameScore() {
        return scores.isEmpty()
                ? Score.from(0)
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
