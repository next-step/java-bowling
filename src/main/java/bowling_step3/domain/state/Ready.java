package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class Ready extends Running {
    public Ready() {
        this(new Scores());
    }

    public Ready(Scores scores) {
        super(scores);
    }

    public Status pitchLast(int numPins) {
        Status status = pitch(numPins);
        if (status instanceof Strike) {
            return new LastBonus(status.scores());
        }
        return status;
    }
}
