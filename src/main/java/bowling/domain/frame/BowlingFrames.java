package bowling.domain.frame;

import bowling.domain.state.FrameBowlStates;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingFrames {

    private final List<Frame> frames;
    private int currentPosition;

    BowlingFrames(List<Frame> frames) {
        if (frames.size() != 10) {
            throw new IllegalArgumentException("invalid frames");
        }

        this.frames = new ArrayList<>(frames);
    }

    public static BowlingFrames newInstance() {
        List<Frame> frames = new ArrayList<>();

        NormalFrame normalFrame = NormalFrame.first();
        frames.add(normalFrame);

        for (int i = 1; i < 9; i++) {
            normalFrame = normalFrame.next();
            frames.add(normalFrame);
        }

        frames.add(new FinalFrame());

        return new BowlingFrames(frames);
    }

    public void play(int downPin) {
        Frame frame = this.frames.get(this.currentPosition);
        frame.play(downPin);

        if (!frame.hasTurn()) {
            this.currentPosition++;
        }
    }

    public List<FrameBowlStates> getFrameStates() {
        return this.frames.stream()
            .map(Frame::getBowlStates)
            .limit(this.currentPosition + 1)
            .collect(Collectors.toList());
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public boolean isFinished() {
        return !this.frames.get(this.frames.size() - 1).hasTurn();
    }
}
