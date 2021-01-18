package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final int lastFrameNumber = 10;

    private final List<Frame> frames;

    public Board() {
        this.frames = new LinkedList<>();
        frames.add(new NormalFrame());
    }

    public void record(int downedPin) {
        if (getLatestFrame().isEnd()) {
            renewFrame();
        }

        getLatestFrame().record(downedPin);
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    private void renewFrame() {
        if (frames.size() == lastFrameNumber - 1) {
            frames.add(new LastFrame());
            return;
        }

        frames.add(new NormalFrame());
    }

    public int getCurrentFrameNumber() {
        if (getLatestFrame().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public boolean isEnd() {
        return frames.size() == lastFrameNumber && getLatestFrame().isEnd();
    }

    public List<String> getResults() {
        return frames.stream()
                .map(Frame::getDescriptionForm)
                .collect(Collectors.toList());
    }
}
