package bowling.domain.state;

import bowling.domain.Score;

public class FirstBowl implements State {
    private static final int SPARE = 10 ;
    private static final int GUTTER = 0;
    private Score score;

    public FirstBowl(int countOfPins) {
        this.score = new Score(countOfPins, 1);
    }

    @Override
    public State bowl(int countOfPins) {
        if(this.score.getScore() + countOfPins == SPARE) {
            return new Spare(this.score.getScore(), countOfPins);
        }
        if(this.score.getScore() == GUTTER && countOfPins == GUTTER) {
            return new Gutter();
        }
        return new Miss(this.score.getScore(), countOfPins);
    }

    @Override
    public Score getScore() {
        return this.score;
    }
}
