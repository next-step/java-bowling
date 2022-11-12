package bowling.domain;

public class NormalFrame implements Frame {
    private int remainPin;
    private Scores scores;

    public NormalFrame(Scores scores) {
        this.remainPin = MAX_PIN;
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
        int score = bowlingStrategy.pitchingBall(remainPin);
        record(score);
        return score;
    }

    private void record(int score) {
        if(remainPin < score) {
            throw new IllegalArgumentException();
        }

        remainPin -= score;
        scores.recordingScore(new Score(score));
    }
}
