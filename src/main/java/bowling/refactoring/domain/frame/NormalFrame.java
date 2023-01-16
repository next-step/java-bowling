package bowling.refactoring.domain.frame;

import bowling.refactoring.domain.*;
import bowling.refactoring.domain.state.*;

public class NormalFrame extends Frame {
    private static final int DEFAULT_BOWL_CHANCE = 2;

    private final Score score;
    private State state;
    private int leftChance;

    public NormalFrame() {
        this.score = new Score();
        this.state = new Running();
        this.leftChance = DEFAULT_BOWL_CHANCE;
    }

    @Override
    void bowl(int fallenPinCount) {
        score.bowl(fallenPinCount);
        leftChance--;

        if (score.isStrike()) {
            this.state = new Strike();
            leftChance = 0;
            return;
        }
        if (score.isSpare()) {
            this.state = new Spare();
            return;
        }
        if (leftChance == 0) {
            this.state = new Normal(score);
        }
    }

    @Override
    boolean isEnd() {
        return state.isEnd();
    }

    @Override
    boolean isEndedCalculateScore() {
        return state.isEndedCalculateScore();
    }

    @Override
    void calculateBonusScore(Frame nextFrame) {
        state.calculateBonusScore(nextFrame);
    }

    public Score score() {
        return this.score;
    }

    public State state() {
        return this.state;
    }
}

