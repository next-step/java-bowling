package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class FrameScores {

    private final List<FrameScore> frameScores = new ArrayList<>();

    public void add(FrameScore firstFrameScore) {
        if (firstFrameScore != null) {
            frameScores.add(firstFrameScore);
        }
    }

    public List<Integer> getCumulativeFrameScores() {
        List<Integer> cumulativeFrameScores = new ArrayList<>();
        frameScores.forEach(frameScore -> accumulate(frameScore, cumulativeFrameScores));
        return cumulativeFrameScores;
    }

    private void accumulate(FrameScore frameScore, List<Integer> cumulativeFrameScores) {
        if (cumulativeFrameScores.isEmpty()) {
            cumulativeFrameScores.add(frameScore.getFrameScoreTotal());
            return;
        }
        int cumulativeFrameScore = cumulativeFrameScores.get(cumulativeFrameScores.size() - 1) + frameScore.getFrameScoreTotal();
        cumulativeFrameScores.add(cumulativeFrameScore);
    }

}
