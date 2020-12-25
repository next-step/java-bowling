package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFrame implements Frame {

    private final List<NormalFrame> frames;

    public LastFrame() {
        frames = new ArrayList<>();
    }

    @Override
    public void record(DownedPin currentTry) {
        if (frames.isEmpty() || getLatestFrame().isEnd()) {
            frames.add(new NormalFrame());
        }

        getLatestFrame().record(currentTry);
    }

    private NormalFrame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    @Override
    public boolean isEnd() {
        if (LastFrameProgress.getProgress(Collections.unmodifiableList(frames))
                == LastFrameProgress.ON_FIRST_PITCH) {
            return false;
        }

        return (LastFrameProgress.getProgress(frames) == LastFrameProgress.ON_ADDITIONAL_PITCH
                && frames.get(0).getFrameStatus() == FrameStatus.MISS)
                || (LastFrameProgress.getProgress(frames) == LastFrameProgress.END);
    }

    @Override
    public int getNumThrown() {
        return LastFrameProgress.countsOfTotalPitch(Collections.unmodifiableList(frames));
    }

    public List<NormalFrame> getFrames() {
        return Collections.unmodifiableList(this.frames);
    }
}
