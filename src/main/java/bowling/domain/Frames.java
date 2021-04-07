package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int MAX_SIZE = 10;
    private static final int MINUS_INDEX_ONE = 1;

    private final List<Frame> frames;
    private final List<FrameScore> scores;

    private Frames() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    private Frames(List<Frame> frames, List<FrameScore> scores) {
        this.frames = frames;
        this.scores = scores;
    }

    public static Frames init() {
        return new Frames();
    }

    public Frames play(int countOfDownPin) {
        List<FrameScore> scores = addScore(countOfDownPin);
        if (!isPitch()) {
            Frame frame = frames.get(frames.size() - MINUS_INDEX_ONE).next(countOfDownPin);
            frames.set(frames.size() - MINUS_INDEX_ONE, frame);
            return new Frames(frames, scores);
        }
        frames.add(Frame.of(countOfDownPin, ofPins()));
        return new Frames(frames, scores);
    }

    public boolean isPitch() {
        if (frames.isEmpty()) {
            return true;
        }
        return isLastIndexFrameEnd();
    }

    public boolean isPlay() {
        if (frames.size() < MAX_SIZE) {
            return true;
        }
        return !isLastIndexFrameEnd();
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public List<FrameScore> scores() {
        return Collections.unmodifiableList(scores);
    }

    public void scoreInit() {
        if (isLastIndexFrameEnd()) {
            scores.add(FrameScore.of(frames.get(frames.size() - MINUS_INDEX_ONE)));
        }
    }

    public boolean isLastIndexFrameEnd() {
        if (frames.isEmpty()) {
            return false;
        }
        return frames.get(frames.size() - MINUS_INDEX_ONE).isEnd();
    }

    private List<FrameScore> addScore(int countOfDownPin) {
        return scores.stream()
                .map(score -> score.addScore(countOfDownPin))
                .collect(Collectors.toList());
    }

    private Pins ofPins() {
        if ((MAX_SIZE - MINUS_INDEX_ONE) == frames.size()) {
            return FinalPins.init();
        }
        return NormalPins.init();
    }
}




