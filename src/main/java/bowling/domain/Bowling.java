package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Bowling {
    private static final int MAX_FRAMES_COUNT = 10;
    private static final int LAST_FRAME_INDEX = MAX_FRAMES_COUNT - 1;
    private static final String WRONG_PLAY_MESSAGE = "playing()이 true가 아닐 때는 play()를 호출할 수 없습니다.";

    private final List<Frame> frames;

    private Bowling(final List<Frame> frames) {
        this.frames = frames;
    }

    public static Bowling init() {
        return new Bowling(Collections.singletonList(NormalFrame.init()));
    }

    public boolean playing() {
        return frames.size() < MAX_FRAMES_COUNT || frames.get(LAST_FRAME_INDEX).playing();
    }

    public Bowling play(final int knockedPinsCount) {
        return play(KnockedPins.from(knockedPinsCount));
    }

    public Bowling play(final KnockedPins knockedPins) {
        validatePlaying();

        final List<Frame> frames = new ArrayList<>(this.frames);
        final Frame lastFrame = frames.get(frames.size() - 1);

        if (lastFrame.playing()) {
            frames.remove(lastFrame);
        }

        final Frame playedFrame = nextFrame(frames, lastFrame).play(knockedPins);
        frames.add(playedFrame);

        return new Bowling(frames);
    }

    private void validatePlaying() {
        if (!playing()) {
            throw new IllegalStateException(WRONG_PLAY_MESSAGE);
        }
    }

    private Frame nextFrame(final List<Frame> frames, final Frame frame) {
        if (frame.playing()) {
            return frame;
        }

        if (frames.size() < LAST_FRAME_INDEX) {
            return frame.next();
        }

        return frame.last();
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public int currentFrameIndex() {
        return frames.size();
    }
}
