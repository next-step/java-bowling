package board;

import frame.Frame;
import frame.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static frame.NormalFrame.LAST_FRAME_NUMBER;

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

    public boolean reachLastFrame() {
        return getNowFrameNumber() == LAST_FRAME_NUMBER;
    }

    public Integer getNowFrameNumber() {
        return frames.getNowFrameNumber();
    }

    public Frame getNowFrame() {
        return frames.getNowFrame();
    }

    public String getName() {
        return name.getName();
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

    public void addFrame(Frame nowFrame) {
        this.frames.add(nowFrame);
    }

    public Frames getFrames() {
        return frames;
    }
}
