package bowling.domain.state;

import bowling.domain.Pitch;

public class FinalStrike extends State {

    private static final String SYMBOL = "X";

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
        return SYMBOL;
    }
}
