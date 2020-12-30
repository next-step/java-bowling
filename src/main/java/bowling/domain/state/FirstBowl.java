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
            System.out.println("Spare");
            return new Spare(this.pitch);
        }

        System.out.println("Miss");
        return new Miss(this.pitch, pitch);
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
