package bowling.domain.ScoreUI;

import bowling.domain.frame.Frame;

public interface ScoreFactory {
    static ScoreFactory of(int score, Frame frame) {
        if (score == 0) {
            return new Miss();
        }
        if (frame.isStrike()) {
            return new Strike();
        }
        if (frame.isSpare()) {
            return new Spare();
        }
        return new Normal(Integer.toString(score));
    }
    String getString();

}
