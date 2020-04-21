package seul.bowling.domain.status;

import seul.bowling.domain.Pins;

public interface Status {
    Status addPins(int clearPin);

    void addBonusScore(int bonusScore);

    boolean end();

    boolean isSpare();

    void addCumulativeScore(int score);

    int getToTalScore();

    boolean endCalculateScore();

    Pins getPins();

    boolean equalsStatus(Status status);
}
