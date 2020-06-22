package camp.nextstep.edu.nextstep8.bowling;

import java.util.*;

public class ScoreBoard {
    private final Map<Integer, Frame> frames = new HashMap<>();

    public void markScore(int frameNumber, int score, int spare) {
        frames.put(frameNumber, new Frame(score, spare));
    }

    public Frame getFrame(int frameNumber) {
        return frames.get(frameNumber);
    }
}
