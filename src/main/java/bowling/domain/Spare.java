package bowling.domain;

public class Spare extends State {
    private static final String SPARE_SYMBOL = "/";

    private int preBowl;

    Spare(int firstBowl, int secondBowl) {
        super(secondBowl, SPARE_SYMBOL);
        this.preBowl = firstBowl;
    }

    @Override
    public State bowl(int firstBowl) {
        return null;
    }
}
