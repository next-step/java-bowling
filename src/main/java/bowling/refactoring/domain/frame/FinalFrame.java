package bowling.refactoring.domain.frame;

import bowling.refactoring.domain.*;
import bowling.refactoring.domain.state.*;

public class FinalFrame extends Frame {
    private static final int BONUS = 1;
    private static final int DEFAULT_BOWL_CHANCE = 2;

    private final Score score;
    private State state;
    private boolean hasBonus;
    private int leftChance;


    public FinalFrame() {
        this.score = new Score();
        this.state = new Running();
        this.hasBonus = false;
        this.leftChance = DEFAULT_BOWL_CHANCE;
    }

    @Override
    void bowl(int fallenPinCount) {
        score.bowl(fallenPinCount);
        leftChance--;

        if (!hasBonus && score.isStrike()) {
            this.leftChance += BONUS;
            hasBonus = true;
        }
        if (!hasBonus && score.isSpare()) {
            this.leftChance += BONUS;
            hasBonus = true;
        }
        if (leftChance == 0 && hasBonus) {
            this.state = new Bonus(score);
        }

        if (leftChance == 0 && !hasBonus) {
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

