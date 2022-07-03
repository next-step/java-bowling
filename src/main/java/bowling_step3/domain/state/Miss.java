package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class Miss extends Done {
    public Miss(Scores scores) {
        super(scores);
    }

    public int calculateAdditionalScore(Status status) {
        throw new UnsupportedOperationException();
    }

    public Status pitchLast(int numPins) {
        return pitch(numPins);
    }
}
