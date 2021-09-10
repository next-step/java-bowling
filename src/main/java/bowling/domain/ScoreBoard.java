package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private List<FrameScore> frameScores = new ArrayList<>();

    public void addPoint(Shot shot, int currentFrame) {
        if (frameScores.size() < currentFrame) {
            frameScores.add(new FrameScore());
        }

        frameScores.stream()
                .filter(frameScore -> !frameScore.isFinished())
                .forEach((frameScore -> frameScore.addPoint(shot)));
    }

    public List<Integer> currentScoreList() {
        List<Integer> scores = new ArrayList<>();

        int sum = 0;

        for (int i = 0; i < frameScores.size(); i++) {
            FrameScore current = frameScores.get(i);
            if (!current.isFinished()) {
                break;
            }

            sum += current.calculateFrameScore();
            scores.add(sum);
        }

        return scores;
    }
}
