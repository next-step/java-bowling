package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int MAX_SIZE = 10;
    private static final int MINUS_INDEX_ONE = 1;

    private final User user;
    private final List<Frame> frames;

    private Frames(User user) {
        this(user, new ArrayList<>());
    }

    private Frames(User user, List<Frame> frames) {
        this.user = user;
        this.frames = frames;
    }

    public static Frames of(User user) {
        return new Frames(user);
    }

    public Frames play(int countOfDownPin) {
        if (!isPitch()) {
            Frame frame = frames.get(frames.size() - MINUS_INDEX_ONE).next(countOfDownPin);
            frames.set(frames.size() - MINUS_INDEX_ONE, frame);
            return new Frames(user, frames);
        }
        frames.add(Frame.of(countOfDownPin, ofPins()));
        return new Frames(user, frames);
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

    public String name() {
        return user.name();
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




