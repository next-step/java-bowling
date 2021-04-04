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
        if (!isSecond()) {
            Frame frame = frames.get(lastIndex()).next(countOfDownPin);
            frames.set(lastIndex(), frame);
            return new Frames(user, frames);
        }
        frames.add(Frame.of(countOfDownPin, ofPins()));
        return new Frames(user, frames);
    }

    private Pins ofPins() {
        if ((MAX_SIZE - MINUS_INDEX_ONE) == frames.size()) {
            return FinalPins.init();
        }
        return NormalPins.init();
    }

    public boolean isSecond() {
        if (frames.isEmpty()) {
            return true;
        }
        return frames.get(lastIndex()).isEnd();
    }

    private int lastIndex() {
        return frames.size() - MINUS_INDEX_ONE;
    }

    public boolean isPlay() {
        return frames.size() < MAX_SIZE;
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public String name() {
        return user.name();
    }
}




