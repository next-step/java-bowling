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
    public abstract Frame nextFrame();

    public void updateScoreByPin(Pin pin) {
        score = score.nextPin(pin);
    }

    public Score score() {
        return score;
    }

    public boolean isNextScore() {
        return score.isNext();
    }

    public static Frame getLastFrame(Frame frame) {
        Frame resultFrame = frame;
        while(resultFrame.nextFrame() != null) {
            resultFrame = resultFrame.nextFrame();
        }
        return resultFrame;
    }

}
