package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class Ready extends Running {
    public Ready() {
        this(new Scores());
    }

    public Ready(Scores scores) {
        super(scores);
    }

    public Status pitch(int numPins) {
        Scores scores = this.scores().pitch(numPins);
        if (scores.isStrike()) {
            return new Strike();
        }
        return new FirstPitch(scores);
    }

    @Override
    public Status pitchLast(int numPins) {
        Scores scores = this.scores().pitchLast(numPins);
        if (scores.isStrike()) {
            return new Strike();
        }
        return new FirstPitch(scores);
    }
}
