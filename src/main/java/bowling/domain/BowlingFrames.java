package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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

    static BowlingFrames newInstance() {
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
        if (!frame.hasTurn()) {
            frame = this.frames.get(++this.currentPosition);
        }

        frame.play(downPin);
    }

    public List<FrameResult> getResults(){
        return this.frames.stream()
            .map(new Function<Frame, FrameResult>() {
                @Override
                public FrameResult apply(Frame frame) {
                    return new FrameResult(frame.getBowlStates());
                }
            })
            .limit(this.currentPosition + 1)
            .collect(Collectors.toList());
    }
}
