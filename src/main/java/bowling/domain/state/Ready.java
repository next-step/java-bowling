package bowling.domain.state;

import bowling.domain.Score;

public class Ready extends Running {
    private static final int STRIKE = 10;

    @Override
    public State bowl(int countOfPins) {
        if(countOfPins == STRIKE) {
            return new Strike();
        }
        return new FirstBowl(countOfPins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public String expression() {
        return "X";
    }

}
