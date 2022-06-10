package bowling.domain;

public class FirstBowl extends State {
    FirstBowl(int firstBowl) {
        validCount(firstBowl);
        this.countOfPins = firstBowl;
        this.symbol = String.valueOf(firstBowl);
    }

    @Override
    public State bowl(int secondBowl) {
        return bowlSecond(secondBowl);
    }
}
