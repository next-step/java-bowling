package bowling.domain.state;

public class Ready implements State {

    @Override
    public State bowl(int countOfPin) {
        if (countOfPin == 10) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
