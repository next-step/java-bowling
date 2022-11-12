package bowling.domain;

public class FinalFrame implements Frame {
    private int remainPin;
    private final FinalScores scores;


    public FinalFrame(Scores scores) {
        this.remainPin = MAX_PIN;
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
        int score = bowlingStrategy.pitchingBall(remainPin);
        record(score);
        return score;
    }

    private void record(int score) {
        if (scores.isBonusPitching() || scores.isStrike()) {
            remainPin = MAX_PIN;
        }

        if (remainPin < score) {
            throw new IllegalArgumentException("잘못된 점수 입니다.");
        }

        scores.recordingScore(new Score(score));
        remainPin -= score;
    }
}
