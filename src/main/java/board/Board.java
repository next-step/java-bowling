package board;

import frame.Frame;
import frame.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {

    private final Name name;
    private final Frames frames;

    public static Board initBoard(String name) {
        return new Board(name, new ArrayList<>());
    }

    public Board(String name, List<Frame> frames) {
        this.name = new Name(name);
        this.frames = new Frames(frames);
    }

    public Integer getNextFrameNumber() {
        return frames.getNextFrameNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(name, board.name) &&
                Objects.equals(frames, board.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }

    public Frame getNowFrame() {
        return frames.getNowFrame();
    }
}
