package bowling.domain;

public abstract class Frame {

    protected int number;
    protected Score score;

    protected Frame() {

    }

    public void pitch(int number) {
        try {
            score.addPin(Pin.of(number));
        } catch (RuntimeException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public abstract boolean canPitch();

    public ScoreType score() {
        return score.status();
    }

    public int number() {
        return number;
    }

    public abstract Frame nextFrame();

    public boolean isEmpty() {
        return score.pins().isEmpty();
    }

    public Pin firstPin() {
        return score.pins().get(0);
    }

    public Pin secondPin() {
        return score.pins().get(1);
    }
}
