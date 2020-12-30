package bowling.domain.state;

import bowling.domain.Pitch;

public class FinalReady extends State {

    private Pitch pitch;

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

}
