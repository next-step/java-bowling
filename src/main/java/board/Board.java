package board;

import frame.Frame;
import frame.Frames;
import frame.LastFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static frame.FrameNumber.LAST_FRAME_NUMBER;


public class Board {

    private final Name name;
    private final Frames frames;

    public Board(String name, List<Frame> frames) {
        this.name = new Name(name);
        this.frames = new Frames(frames);
    }

    public static Board initBoard(String name) {
        return new Board(name, new ArrayList<>());
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

    public void addLast(Frame frame) {
        this.frames.add(9, frame);
    }

    public Frames getFrames() {
        return frames;
    }

    public LastFrame getLastFrame() {
        return frames.getFrames().stream()
                .filter(frame -> frame instanceof LastFrame)
                .findFirst()
                .map(frame -> (LastFrame) frame)
                .orElse(LastFrame.init());
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
}
