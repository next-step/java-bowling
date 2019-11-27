package score.framescore;

import score.ScoreInfoBundle;

import java.util.ArrayList;
import java.util.List;

public class LastFrameScores {
    private final List<FrameScore> frameScores = new ArrayList<>();

    public FrameScore sum() {
        if (frameScores.size() == 0) {
            return new FrameScore(0, 1);
        }

        int score = frameScores.stream()
                .mapToInt(FrameScore::getScore)
                .sum();
        int addCount = frameScores.stream()
                .mapToInt(FrameScore::getAddCount)
                .sum();
        return new FrameScore(score, addCount);
    }

    public void addFirst(ScoreInfoBundle scores) {
        if (scores.size() < 1) {
            return;
        }
        FrameScore frameScore = scores.calculateLast(0);
        this.frameScores.add(frameScore);
    }

    public void addSecond(ScoreInfoBundle scores) {
        if (scores.size() < 2) {
            return;
        }
        FrameScore frameScore = calculateSecond(scores);
        frameScores.add(frameScore);
    }

    private FrameScore calculateSecond(ScoreInfoBundle scores) {
        FrameScore frameScore = scores.calculateLast(1);
        return new FrameScore(frameScore.getScore(), 0);
    }

    public void addThird(ScoreInfoBundle scores) {
        if (scores.size() != 3) {
            return;
        }

        FrameScore frameScore = calculateThird(scores);
        frameScore = new FrameScore(frameScore.getScore(), 0);
        frameScores.add(frameScore);
    }

    private FrameScore calculateThird(ScoreInfoBundle scores) {
        FrameScore frameScore = scores.calculateLast(2);
        frameScore = new FrameScore(frameScore.getScore(), 0);
        return frameScore;
    }
}
