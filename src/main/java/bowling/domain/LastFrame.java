package bowling.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

        return (numThrown() == 2 && frames.get(0).getFrameStatus() == FrameStatus.MISS)
                || (numThrown() == 3);
    }

    public int numThrown() {
        return frames.stream()
                .map(NormalFrame::numThrown)
                .reduce(0, Integer::sum);
    }

    @Override
    public List<DownedPin> exportCurrentStatus() {
        return frames.stream()
                .map(NormalFrame::exportCurrentStatus)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private NormalFrame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }
}
