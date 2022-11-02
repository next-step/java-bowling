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

    public boolean isEnd() {
        return !canPitch();
    }

    public abstract boolean canPitch();

    public abstract Frame nextFrame();

    public ScoreType status() {
        return score.status();
    }

    public int number() {
        return number;
    }

    public boolean isEmpty() {
        return score.pins().isEmpty();
    }

    public int pinNumber(int index) {
        return score.pinNumber(index);
    }

    public int pinsSize() {
        return score.pins().size();
    }
}
