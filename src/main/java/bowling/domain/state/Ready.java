package bowling.domain.state;

import bowling.domain.Pitch;

public class Ready extends State {

    private Pitch pitch;

    @Override
    public State bowl(Pitch pitch) {
        if(pitch.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pitch);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

}
