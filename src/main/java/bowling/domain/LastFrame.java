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

    @Override
    public boolean isEnd() {
        if (frames.isEmpty()) {
            return false;
        }

        return (getNumThrown() == 2 && frames.get(0).getFrameStatus() == FrameStatus.MISS)
                || (getNumThrown() == 3);
    }

    @Override
    public int getNumThrown() {
        return frames.stream()
                .map(NormalFrame::getNumThrown)
                .reduce(0, Integer::sum);
    }

    public List<NormalFrame> getFrames() {
        return Collections.unmodifiableList(this.frames);
    }

    private NormalFrame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }
}
