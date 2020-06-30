package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class FrameScores {
    private static final int ZERO = 0;

    private final List<FrameScore> frameScores;

    private FrameScores(List<FrameScore> frameScores) {
        this.frameScores = frameScores;
    }

    public static FrameScores of(List<FrameScore> frameScores) {
        return new FrameScores(frameScores);
    }

    public List<Integer> getFrameScores() {
        List<Integer> cumulativeFrameScores = new ArrayList<>();
        int cumulativeFrameScore = ZERO;
        for (FrameScore frameScore : frameScores) {
            cumulativeFrameScore += frameScore.getFrameScore();
            cumulativeFrameScores.add(cumulativeFrameScore);
        }
        return cumulativeFrameScores;
    }
}
