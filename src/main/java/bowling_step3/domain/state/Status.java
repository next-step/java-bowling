package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public interface Status {
    Status pitch(int numPins);
    boolean isFinished();

    int calculateAdditionalScore(Status status);

    Scores scores();

    Integer getScore();

    Status pitchLast(int numPins);
}
