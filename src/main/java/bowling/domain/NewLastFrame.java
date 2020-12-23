package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NewLastFrame implements Frame {

    private final List<NormalFrame> frames;

    public NewLastFrame() {
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

        return (numThrown() == 2 && frames.get(0).getFrameStatus() == FrameStatus.MISS)
                || (numThrown() == 3);
    }

    public int numThrown() {
        return frames.stream()
                .map(NormalFrame::numThrown)
                .reduce(0, Integer::sum);
    }

    private NormalFrame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }
}
