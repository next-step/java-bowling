package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.Objects;

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

    public int currentFrameNumber() {
        return frames.currentFrameNumber();
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
    }

    public List<Frame> frames() {
        return frames.frames();
    }

    public String name() {
        return user.name();
    }

    public User user() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(user, board.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
