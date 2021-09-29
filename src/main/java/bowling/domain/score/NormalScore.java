package bowling.domain.score;

public class NormalScore implements Score {

    private Pin first;
    private Pin second;

    private NormalScore(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static Score emptyScore() {
        return new NormalScore(null, null);
    }

    @Override
    public Score saveNextPin(Pin pin) {
        return null;
    }

    @Override
    public boolean isNextStorable() {
        return false;
    }

}
