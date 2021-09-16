package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public abstract class Frame {

    final int round;
    Score score;

    Frame(int round, Score score) {
        this.round = round;
        this.score = score;
    }

    public abstract Frame createNextFrame();
    public abstract void updateScoreByPin(Pin pin);

}
