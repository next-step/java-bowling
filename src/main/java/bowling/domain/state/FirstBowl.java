package bowling.domain.state;

public class FirstBowl implements State {
    private final int firstPis;

    public FirstBowl(int firstPins) {
        this.firstPis = firstPins;
    }

    @Override
    public State pitch(int fallenPins) {
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(firstPis);
    }
}
