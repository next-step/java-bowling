package bowling.domain.dto;

import java.util.List;

public class FrameInfo {

    private final List<Integer> pinDownResults;
    private final int score;
    private final boolean isFinalFrame;
    private final boolean isSpare;
    private final boolean isScoreDecided;

    public FrameInfo(List<Integer> pinDownResults, int score, boolean isFinalFrame, boolean isSpare, boolean isScoreDecided) {
        this.pinDownResults = pinDownResults;
        this.score = score;
        this.isFinalFrame = isFinalFrame;
        this.isSpare = isSpare;
        this.isScoreDecided = isScoreDecided;
    }

    public List<Integer> getPinDownResults() {
        return pinDownResults;
    }

    public int getScore() {
        return score;
    }

    public boolean isFinalFrame() {
        return isFinalFrame;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isScoreDecided() {
        return isScoreDecided;
    }
}
