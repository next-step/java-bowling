package bowling_step3.domain.state;


import bowling_step3.domain.Pitch;
import bowling_step3.domain.Score;

import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Spare extends Finished {
    private final Pitch firstPins;
    private final Pitch secondPins;

    public Spare(int firstPins, int secondPins) {
        this(Pitch.valueOf(firstPins), Pitch.valueOf(secondPins));
    }

    public Spare(Pitch firstPins, Pitch secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public int getPitchCount() {
        return 2;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public int getTotalCount() {
        return BOWLING_MAX_NUMBER;
    }

    @Override
    public String toString() {
        return firstPins.toString() + "|/";
    }
}
