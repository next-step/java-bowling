package bowling.domain;

import java.util.Objects;

public abstract class Frame {
    public static final int WAITING_CALCULATION = -1;

    Pin pin;
    States states;
    Frame nextFrame;

    abstract void bowl(Pin pin);
    abstract boolean isEndFrame();
    abstract boolean isEndGame();
    abstract Frame getNextFrame(int frameNumber);
    abstract int getScore();
    public abstract States getStates();

    int calculateAdditionalScore(Score score) {
        for (Pin pin : states.getPins()) {
            score = score.bowl(pin.getFallenPin());

            if (score.canCalculateScore()) {
                return score.getScore();
            }
        }

        if (Objects.isNull(nextFrame)) {
            return WAITING_CALCULATION;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    boolean isCalculateScore() {
        return getScore() != WAITING_CALCULATION;
    }
}
