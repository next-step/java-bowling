package bowling.domain.score;

public class NormalScore extends Score {

    private NormalScore(Pin first, Pin second) {
        super(first, second);
    }

    public static NormalScore empty() {
        return new NormalScore(null, null);
    }

    public static NormalScore ofFirst(Pin pin) {
        return new NormalScore(pin, null);
    }

}
