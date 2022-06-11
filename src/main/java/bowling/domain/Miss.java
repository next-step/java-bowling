package bowling.domain;

public class Miss extends State {
    private int preBowl;

    Miss(int firstBowl, int secondBowl) {
        super(secondBowl, String.valueOf(secondBowl));
        this.preBowl = firstBowl;
    }

    @Override
    public State bowl(int firstBowl) {
        return null;
    }
}
