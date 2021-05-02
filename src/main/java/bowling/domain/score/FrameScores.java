package bowling.domain.score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameScores {
    private final Map<Integer, FrameScore> frameScores;

    public FrameScores() {
        frameScores = new HashMap<>();
    }

    public void addScore(int frameNumber, int score) {
        if (frameScores.get(frameNumber) == null) {
            frameScores.put(frameNumber, new FrameScore());
        }
        frameScores.get(frameNumber).addScore(score);
    }

    public List<String> getFrameScore(int frameNumber) {
        return frameScores.get(frameNumber).getFormattedScores();
    }
}
