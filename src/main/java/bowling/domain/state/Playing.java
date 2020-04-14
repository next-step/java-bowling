package bowling.domain.state;

public class Playing implements State{

    private final int falledPin;

    public Playing(int falledPin) {
        this.falledPin = falledPin;
    }

    @Override
    public State play(int falledPin) {
        return null;
    }
}
