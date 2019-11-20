package bowling.domain.state;

public class Ready extends State {

    public Ready() {
        this.playCount = 0;
        this.remainPinCount = 10;
    }

    @Override
    public State play(int hitCount) {
        if (hitCount == 10) {
            return new Strike();
        }

        if (hitCount == 0) {
            return new Gutter();
        }

        return new Hit(remainPinCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
