package bowling.domain.state;

import bowling.domain.Score;

public class Ready implements State {
    private static final int STRIKE = 10;

    @Override
    public State bowl(int countOfPins) {
        if(countOfPins == STRIKE) {
            return new Strike();
        }
        return new FirstBowl(countOfPins);
    }

    @Override
    public Score getScore() {
        return null;
    }
}
