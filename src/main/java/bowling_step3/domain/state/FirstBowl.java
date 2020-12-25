package bowling_step3.domain.state;


import bowling_step3.domain.Pitch;

public class FirstBowl extends Running {
    private final Pitch firstPins;

    public FirstBowl(int firstPins) {
        this.firstPins = Pitch.valueOf(firstPins);
    }

    @Override
    public State pitch(int fallenPins) {
        if (firstPins.isSpare(Pitch.valueOf(fallenPins))) {
            return new Spare(firstPins, Pitch.valueOf(fallenPins));
        }

        return new Miss(firstPins, Pitch.valueOf(fallenPins));
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public int getTotalCount() {
        return firstPins.getKnockDown();
    }

    @Override
    public String toString() {
        return firstPins.toString();
    }
}
