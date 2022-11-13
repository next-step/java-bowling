package bowling.domain;

import java.util.ArrayList;

public class BonusFrame implements Frame {
    private final Scores scores;
    private FrameResult result;

    public BonusFrame() {
        this.scores = new Scores(new ArrayList<>());
    }

    public BonusFrame(Scores scores) {
        this.scores = scores;
    }

    @Override
    public boolean end() {
        if (scores.size() == 3) {
            return true;
        }

        if (scores.sum().equals(new Score(10)) || scores.size() == 2) {
            result = FrameResult.match(scores);
            return !isNeedBonus();
        }

        return false;
    }

    private boolean isNeedBonus() {
        return result == FrameResult.STRIKE || result == FrameResult.SPARE;
    }

    @Override
    public void addScore(Score score) {
        scores.add(score);
    }

    @Override
    public Scores getScores() {
        return scores;
    }

    @Override
    public FrameResult getResult() {
        return result;
    }
}
