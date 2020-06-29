package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final int finalRound;
    private final LinkedList<Frame> frames;

    public Frames(int finalRound) {
        this.finalRound = finalRound;
        this.frames = new LinkedList<>();
        this.frames.add(new Frame());
    }

    public List<List<ResultType>> getGameResult() {
        return frames.stream().map(Frame::getResult).collect(Collectors.toList());
    }

    public int getCurrentRound() {
        return frames.size();
    }

    public boolean bowling(int pin) {
        boolean isFrameFinish = frames.getLast().addScore(pin);
        if (isFrameFinish) {
            frames.add(new Frame());
        }
        return isFrameFinish;
    }
}
