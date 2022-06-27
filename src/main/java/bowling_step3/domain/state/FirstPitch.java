package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class FirstPitch implements State {
    private Scores scores;

    public FirstPitch(Scores scores) {
        this.scores = scores;
    }

    public State pitch(int numPins) {
        Scores scores = this.scores.pitch(numPins);
        if (scores.isSpare()) {
            return new Spare(scores);
        }
        return new Miss(scores);
    }
}
