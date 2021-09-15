package bowling.domain.frame;

import bowling.domain.score.Score;

public abstract class Frame {

    final int round;
    Score score;
    Frame nextFrame;

    Frame(int round, Score score, Frame nextFrame) {
        this.round = round;
        this.score = score;
        this.nextFrame = nextFrame;
    }

    public abstract Frame createNextFrame();
    public abstract void saveScore(Score score);

}
