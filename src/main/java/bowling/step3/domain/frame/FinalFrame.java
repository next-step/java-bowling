package bowling.step3.domain.frame;

import bowling.step3.domain.scores.Scores;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static Frame of(int frame, Scores scores) {
        return new FinalFrame(frame, scores);
    }

    public void createNextFrame (Scores scores) {
        this.scores = scores;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    protected int calculateScoreOfStrike() {
        return scores.stream()
                     .reduce(0, (total, score) -> total + score.getValue(), Integer::sum);
    }

    @Override
    protected int calculateScoreOfSpared() {
        return calculateScoreOfStrike();
    }
}