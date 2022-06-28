package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class FirstPitch extends Running {
    public FirstPitch(Scores scores) {
        super(scores);
    }
    public Status pitch(int numPins) {
        Scores scores = this.scores().pitch(numPins);
        if (scores.isSpare()) {
            return new Spare(scores);
        }
        return new Miss(scores);
    }

    public Status pitchLast(int numPins) {
        Status status = pitch(numPins);
        if (status instanceof Spare) {
            return new LastBonus(status.scores());
        };
        return status;
    }
}
