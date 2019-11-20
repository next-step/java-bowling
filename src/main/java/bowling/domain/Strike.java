package bowling.domain;

public class Strike extends State {

    public Strike() {
        remainPinCount = 0;
    }

    @Override
    public State play(int hitCount) {
        return this;
    }
}
