package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class GameOver extends Done {
    public GameOver(Scores scores) {
        super(scores);
    }

    public int calculateAdditionalScore(Status status) {
        throw new UnsupportedOperationException();
    }

    public Status pitchLast(int numPins) {
        throw new UnsupportedOperationException();
    }
}
