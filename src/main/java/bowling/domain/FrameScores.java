package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameScores {
    private final List<FrameScore> frameScores;
    private int totalScore;

    public FrameScores() {
        this.frameScores = new ArrayList<>();
        this.totalScore = 0;
    }

    public void addScore(FrameScore frameScore) {
        if(!frameScore.isNotScore()) {
            totalScore = frameScore.addTotalScore(totalScore);
        }
        frameScores.add(frameScore);
    }

    public List<FrameScore> getFrameScores() {
        return Collections.unmodifiableList(this.frameScores);
    }

    @Override
    public String toString() {
        return "FrameScores{" +
                "frameScores=" + frameScores +
                ", totalScore=" + totalScore +
                '}';
    }
}
