package bowling.domain.state;

public class Strike implements State{

    @Override
    public State play(int falledPin) {
        return null;
    }
}
