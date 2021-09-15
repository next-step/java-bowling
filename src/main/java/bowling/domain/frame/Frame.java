package bowling.domain.frame;

import bowling.domain.score.Score;

public abstract class Frame {

    final int round;
    final Score score;
    final Frame nextFrame;

    Frame(int round, Score score, Frame nextFrame) {
        this.round = round;
        this.score = score;
        this.nextFrame = nextFrame;
    }

}
