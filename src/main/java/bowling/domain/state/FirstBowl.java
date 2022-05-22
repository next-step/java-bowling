package bowling.domain.state;

public class FirstBowl implements State {
    private static final int SPARE = 10 ;
    private final int countOfPins;

    public FirstBowl(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    @Override
    public State bowl(int countOfPins) {
        if(this.countOfPins + countOfPins == SPARE) {
            return new Spare();
        }
        return new Miss(this.countOfPins, countOfPins);
    }
}
