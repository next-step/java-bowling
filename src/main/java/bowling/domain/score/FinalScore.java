package bowling.domain.score;

public class FinalScore extends Score {

    private final Pin bonus;

    private FinalScore(Pin first, Pin second, Pin bonus) {
        super(first, second);
        this.bonus = bonus;
    }

    public static FinalScore empty() {
        return new FinalScore(null, null, null);
    }

    public FinalScore createFirstPin(Pin pin) {
        return new FinalScore(pin, null, null);
    }

}
