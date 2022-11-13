package bowling.domain.frame;

import bowling.domain.score.FinalScores;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.strategy.BowlingStrategy;

public class FinalFrame implements Frame {
    private final FinalScores scores;


    public FinalFrame(Scores scores) {
        this.scores = (FinalScores) scores;
    }

    @Override
    public String score() {
        return scores.toString();
    }

    @Override
    public boolean availablePitching() {
        if (scores.isFirstPitching() || scores.isSecondPitching()) {
            return true;
        }

        return scores.isBonusPitching() && scores.isSpare();
    }

    @Override
    public int pitch(BowlingStrategy bowlingStrategy) {
        if (!availablePitching()) {
            return 0;
        }
        int score = bowlingStrategy.pitchingBall(scores.remainPin());
        record(score);
        return score;
    }

    private void record(int score) {
        if (scores.remainPin() < score) {
            throw new IllegalArgumentException("잘못된 점수 입니다.");
        }

        scores.recordingScore(new Score(score));
    }
}
