package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class Board {
    private final Frames frames;
    private final User user;

    public Board(String name) {
        frames = new Frames();
        user = new User(name);
    }

    public int bowl(int pins) {
        return frames.bowl(pins);
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
    }

    public List<Frame> frames() {
        return frames.frames();
    }

    public User user() {
        return user;
    }
}
