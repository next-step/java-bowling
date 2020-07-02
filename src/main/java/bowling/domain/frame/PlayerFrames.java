package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerFrames {

    private static final int FRAME_COUNT = 10;

    private final List<Frame> frames;
    private int currentPosition;

    PlayerFrames(List<Frame> frames) {
        validate(frames);

        this.frames = new ArrayList<>(frames);
    }

    public static PlayerFrames newInstance() {
        List<Frame> frames = new ArrayList<>();

        Frame normalFrame = Frame.first();
        frames.add(normalFrame);

        for (int i = 1; i < FRAME_COUNT - 1; i++) {
            normalFrame = normalFrame.next();
            frames.add(normalFrame);
        }

        frames.add(frames.get(frames.size() - 1).last());

        return new PlayerFrames(frames);
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

    public List<FrameResult> getFrameResults() {
        return this.frames.stream()
            .map(Frame::getFrameResult)
            .collect(Collectors.toList());
    }


    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public boolean isFinished() {
        return !this.frames.get(this.frames.size() - 1).hasTurn();
    }

}
