package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class FirstPitch extends Running {
    private Scores scores;

    public FirstPitch(Scores scores) {
        this.scores = scores;
    }

    public Status pitch(int numPins) {
        Scores scores = this.scores.pitch(numPins);
        if (scores.isSpare()) {
            return new Spare(scores);
        }
        return new Miss(scores);
    }
}
