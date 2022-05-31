package bowling.domain.state;

public class Ready implements State {

    @Override
    public State bowl(int countOfPin) {
        if (countOfPin == 10) {
            return State.ofStrike();
        }

        return State.ofFirstBowl(countOfPin);
    }

}
