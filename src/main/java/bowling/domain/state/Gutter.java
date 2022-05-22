package bowling.domain.state;

import bowling.domain.Score;

public class Gutter implements State{
    private Score score;
    public Gutter() {
        this.score = Score.ofGutter();
    }
    @Override
    public State bowl(int countOfPins) {
        return null;
    }

    @Override
    public Score getScore() {
        return this.score;
    }
}
