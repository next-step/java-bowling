package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class Ready implements State {
    private Scores scores;

    public Ready() {
        this.scores = new Scores();
    }

    public State pitch(int numPins) {
        Scores scores = this.scores.pitch(numPins);
        if (scores.isStrike()) {
            return new Strike();
        }
        return new FirstPitch(scores);
    }
}
