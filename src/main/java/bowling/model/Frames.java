package bowling.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Frames implements Iterable<Frame> {
    private static final int NORMAL_FRAME_SIZE = 9;
    public static final int FRAME_SIZE = 10;

    private final List<Frame> frames = new ArrayList<>();
    private Frame currentPlayingFrame;

    public Frames() {
        this.currentPlayingFrame = new NormalFrame();
        this.frames.add(this.currentPlayingFrame);
    }

    private Frame currentPlayingFrame() {
        return currentPlayingFrame;
    }

    public void record(ShotResult shotResult) {
        if (isOver()) {
            return;
        }
        currentPlayingFrame().record(shotResult);
        if (currentPlayingFrame.isOver()) {
            changeCurrentPlayingFrame();
        }
    }

    private void changeCurrentPlayingFrame() {
        frames.add(currentPlayingFrame);
        currentPlayingFrame = frames.size() == NORMAL_FRAME_SIZE ? new FinalFrame() : new NormalFrame();
    }

    public boolean isOver() {
        return frames.size() == FRAME_SIZE;
    }

    public int currentPlayingFrameIndex() {
        return frames.size();
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }
}
