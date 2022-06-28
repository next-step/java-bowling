package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class FirstPitch extends Running {
    public FirstPitch(Scores scores) {
        super(scores);
    }

    public Status pitchLast(int numPins) {
        if (scores().isSpare()) {
            return new LastBonus(this.scores());
        }
        return new Miss(this.scores());
    }
}
