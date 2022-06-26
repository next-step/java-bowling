package bowling.domain.state;

public class FirstBowl extends Running {

    public FirstBowl(int firstBowl) {
        super(firstBowl, String.valueOf(firstBowl));
    }

    @Override
    public AbstractState bowl(int fallenPins) {
        return bowlSecond(fallenPins);
    }
}
