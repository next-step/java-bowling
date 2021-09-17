package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Frames {
    private static final int FINAL_FRAME = 10;

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        this.frames.add(new NormalFrame());
    }

    public Frames(int frameNo) {
        this();
        IntStream.range(1, frameNo).forEach(i -> this.next(10));
    }

    public Frames next(int number) {
        if (lastFrame().finish()) {
            this.frames.add(lastFrame().next(number));
            return this;
        }
        lastFrame().next(number);
        return this;
    }

    private Frame lastFrame() {
        return this.frames.get(this.frames.size() - 1);
    }

    public int nextFrameNo() {
        if (lastFrame().finish()) {
            return this.frames.size() + 1;
        }
        return this.frames.size();
    }

    public boolean finish() {
        return frames.size() == FINAL_FRAME && turnFinish();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public boolean turnFinish(){
        return lastFrame().finish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
