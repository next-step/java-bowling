package bowling.domain.frame;

import bowling.domain.state.PinsState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BowlingFrames {

    private static final int FRAME_COUNT = 10;

    private final List<Frame> frames;
    private int currentPosition;

    BowlingFrames(List<Frame> frames) {
        validate(frames);

        this.frames = new ArrayList<>(frames);
    }

    public static BowlingFrames newInstance() {
        List<Frame> frames = new ArrayList<>();

        Frame normalFrame = Frame.first();
        frames.add(normalFrame);

        for (int i = 1; i < FRAME_COUNT - 1; i++) {
            normalFrame = normalFrame.next();
            frames.add(normalFrame);
        }

        frames.add(frames.get(frames.size()-1).last());

        return new BowlingFrames(frames);
    }

    private void validate(List<Frame> frames) {
        if (frames.size() != FRAME_COUNT) {
            throw new IllegalArgumentException("invalid frames");
        }
    }

    public void play(int downPin) {
        Frame frame = this.frames.get(this.currentPosition);
        frame.play(downPin);

        if (!frame.hasTurn()) {
            this.currentPosition++;
        }
    }

    public List<PinsState> getPinsStates() {
        return this.frames.stream()
            .map(Frame::createPinState)
            .collect(Collectors.toList());
    }

    public List<Integer> computeScores(){
        return this.frames.stream()
            .map(Frame::getScore)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public boolean isFinished() {
        return !this.frames.get(this.frames.size() - 1).hasTurn();
    }
}
