package camp.nextstep.edu.nextstep8.bowling;

import java.util.*;

public class ScoreBoard {
    private static final String EMPTY = "";
    private final Map<Integer, Frame> frames;

    public ScoreBoard(Map<Integer, Frame> frames) {
        this.frames = frames;
    }

    public String getFrameResult(int index) {
        return Optional.ofNullable(frames.get(index))
                .map(Frame::getResultSymbol)
                .orElse(EMPTY);
    }

    public int getFrameCount() {
        return frames.size();
    }
}
