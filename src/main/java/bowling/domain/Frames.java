package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final int finalRound;
    private final List<Frame> frames;

    public Frames(int finalRound) {
        this.finalRound = finalRound;
        this.frames = new LinkedList<>();
    }

    public List<String> getGameResult() {
        return frames.stream().map(Frame::getResult).collect(Collectors.toList());
    }

    public int getCurrentRound() {
        return frames.size();
    }
}
