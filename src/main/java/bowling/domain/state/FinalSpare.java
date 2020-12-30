package bowling.domain.state;

import bowling.domain.Pitch;

public class FinalSpare extends State {
    private static final String SYMBOL = "/";
    private static final String DELIMITER = "|";

    private final Pitch firstPitch;

    public FinalSpare(Pitch firstPitch) {
        this.firstPitch = firstPitch;
    }

    @Override
    public State bowl(Pitch pitch) {
        if(pitch.isStrike()) {
            return new FinalStrike();
        }
        return new FinalBowl(pitch);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String toString() {
        return firstPitch + DELIMITER + SYMBOL;
    }
}
