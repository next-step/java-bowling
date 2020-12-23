package bowling.domain.state;

import bowling.domain.Pitch;

public class FinalBowl extends State {
    private static final int MAX_SCORE = 10;

    private Pitch pitch;

    public FinalBowl(Pitch pitch) {
        this.pitch = pitch;
    }

    @Override
    public State bowl(Pitch pitch) {
        if(this.pitch.getScore() + pitch.getScore() == MAX_SCORE) {
            return new FinalSpare(this.pitch);
        }

        return new FinalMiss(this.pitch, pitch);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String toString() {
        return "" + pitch;
    }
}
