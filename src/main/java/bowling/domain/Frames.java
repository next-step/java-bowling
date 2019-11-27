package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frames {

    public final int FIRST_FRAME = 1;
    public final int FINAL_FRAME = 10;

    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new Frame(FIRST_FRAME));
    }

    public boolean hasNext() {
        return !isFinalFrameFinished();
    }

    public int getThisTurn() {
        return frames.size();
    }

    public void bowl(int pin) {
        Frame latest = getLatestFrame();
        latest.bowl(pin);
        if (latest.isFinished() && !isFinalFrameFinished()) {
            frames.add(latest.ofNext());
        }
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    private boolean isFinalFrameFinished() {
        return frames.size() == FINAL_FRAME && getLatestFrame().isFinished();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return FIRST_FRAME == frames1.FIRST_FRAME &&
                FINAL_FRAME == frames1.FINAL_FRAME &&
                Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FIRST_FRAME, FINAL_FRAME, frames);
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}
