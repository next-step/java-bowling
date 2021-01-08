package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFrame implements Frame {

    private static final int MAXIMUM_NUMBER_OF_PITCHES_IN_LAST_FRAME = 3;

    private final List<NormalFrame> frames;
    private int numPitches;

    public LastFrame() {
        this.frames = new ArrayList<>();
        frames.add(new NormalFrame());
        this.numPitches = 0;
    }

    @Override
    public void record(int numDownedPins) {
        if (getLatestFrame().isEnd()) {
            frames.add(new NormalFrame());
        }

        if (getInitialFrame().isEnd() && getInitialFrame().needAdditionalScore()) {
            getInitialFrame().recordAdditionalScore(numDownedPins);
        }

        getLatestFrame().record(numDownedPins);
        numPitches += 1;
    }

    @Override
    public boolean isEnd() {
        FrameStatus initialFrameStatus = getInitialFrame().decideStatus();

        return initialFrameStatus == FrameStatus.MISS ||
                ((initialFrameStatus == FrameStatus.STRIKE || initialFrameStatus == FrameStatus.SPARE) &&
                        this.numPitches == MAXIMUM_NUMBER_OF_PITCHES_IN_LAST_FRAME);
    }

    @Override
    public int calculateScore() {
        return getInitialFrame().calculateScore();
    }

    @Override
    public boolean needAdditionalScore() {
        return getInitialFrame().needAdditionalScore();
    }

    public List<NormalFrame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private NormalFrame getInitialFrame() {
        return frames.get(0);
    }

    private NormalFrame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }
}
