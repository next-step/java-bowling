package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int MAX_SIZE = 10;
    private static final int MINUS_INDEX_ONE = 1;

    private final User user;
    private final List<Frame> frames;
    private final List<FrameScore> scores;

    private Frames(User user) {
        this(user, new ArrayList<>(), new ArrayList<>());
    }

    private Frames(User user, List<Frame> frames, List<FrameScore> scores) {
        this.user = user;
        this.frames = frames;
        this.scores = scores;
    }

    public static Frames of(User user) {
        return new Frames(user);
    }

    public Frames play(int countOfDownPin) {
        List<FrameScore> scores = addScore(countOfDownPin);
        if (!isPitch()) {
            Frame frame = frames.get(frames.size() - MINUS_INDEX_ONE).next(countOfDownPin);
            frames.set(frames.size() - MINUS_INDEX_ONE, frame);
            return new Frames(user, frames, scores);
        }
        frames.add(Frame.of(countOfDownPin, ofPins()));
        return new Frames(user, frames, scores);
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

    public String name() {
        return user.name();
    }

    public void scoreInit() {
        if (isLastIndexFrameEnd()) {
            scores.add(FrameScore.of(frames.get(frames.size() - MINUS_INDEX_ONE)));
        }
    }

    private List<FrameScore> addScore(int countOfDownPin) {
        return scores.stream()
                .map(score -> score.add(countOfDownPin))
                .collect(Collectors.toList());
    }

    private Pins ofPins() {
        if ((MAX_SIZE - MINUS_INDEX_ONE) == frames.size()) {
            return FinalPins.init();
        }
        return NormalPins.init();
    }

    private boolean isLastIndexFrameEnd() {
        return frames.get(frames.size() - MINUS_INDEX_ONE).isEnd();
    }
}




