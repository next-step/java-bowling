package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishGameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private List<Frame> frames;
    private boolean isFinish;

    public Frames() {
        this.frames = new ArrayList<>();
        init();
    }

    public Frames(final boolean isFinish) {
        this.isFinish = isFinish;
    }

    private void init() {
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame());
        }
        frames.add(new FinalFrame());
    }

    public void roll(final Score score) {
        if (this.isFinish) {
            throw new FinishGameException();
        }

        Frame frame = frames.stream()
                .filter(f -> !f.isFinish())
                .findFirst()
                .orElseThrow(FinishGameException::new);

        frame.roll(score);

        this.isFinish = this.frames.stream().allMatch(Frame::isFinish);
    }

    public boolean isFinish() {
        return this.isFinish;
    }

    public List<Frame> elements() {
        return Collections.unmodifiableList(this.frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                ", isFinish=" + isFinish +
                '}';
    }
}
