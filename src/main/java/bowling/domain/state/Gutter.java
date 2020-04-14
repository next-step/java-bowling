package bowling.domain.state;

public class Gutter implements State {

    @Override
    public State play(int falledPin) {
        return null;
    }
}
