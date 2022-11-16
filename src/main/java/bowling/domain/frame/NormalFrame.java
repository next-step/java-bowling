package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.strategy.BowlingStrategy;

public class NormalFrame implements Frame {
    private final Scores scores;

    public NormalFrame(Scores scores) {
        this.scores = scores;
    }

    @Override
    public String score() {
        return scores.toString();
    }

    @Override
    public boolean availablePitching() {
        if(scores.isFirstPitching()) {
            return true;
        }

        return !scores.isStrike() && scores.isSecondPitching();
    }

    @Override
    public int pitch(BowlingStrategy bowlingStrategy) {
        int score = bowlingStrategy.pitchingBall(scores.remainPin());
        record(score);
        return score;
    }

    private void record(int score) {
        if(scores.remainPin() < score) {
            throw new IllegalArgumentException();
        }
        scores.recordingScore(new Score(score));
    }
}
