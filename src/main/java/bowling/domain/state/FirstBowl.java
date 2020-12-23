package bowling.domain.state;

import bowling.domain.Pitch;

public class FirstBowl extends State {

    private static final int MAX_SCORE = 10;

    private Pitch pitch;

    public FirstBowl(Pitch pitch) {
        this.pitch = pitch;
    }

    @Override
    public State bowl(Pitch pitch) {
        if(this.pitch.getScore() + pitch.getScore() == MAX_SCORE) {
            return new Spare(this.pitch);
        }

        return new Miss(this.pitch, pitch);
    }
}
