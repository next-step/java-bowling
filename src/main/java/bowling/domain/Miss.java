package bowling.domain;

public class Miss extends State {

    public Miss(int remainPinCount) {
        this.remainPinCount = remainPinCount;
    }

    @Override
    public State play(int hitCount) {
        return this;
    }
}
