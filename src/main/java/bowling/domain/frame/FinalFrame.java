package bowling.domain.frame;

import bowling.domain.state.*;
import bowling.exception.BowlingFrameException;
import org.springframework.util.ObjectUtils;

public class FinalFrame extends Frame {
    protected State bonusState;

    FinalFrame() {
        state = new Ready();
    }

    @Override
    public Frame next(int number) {
        if(!state.stateFinish()){
            state = state.bowl(number);
            return this;
        }
        if(firstBonus()){
            bonusState = new Bonus(number, state.getBonusCount());
            return this;
        }
        if(secondBonus()){
            bonusState = bonusState.bowl(number);
            return this;
        }
        throw new BowlingFrameException("투구가 모두 종료되었습니다.");
    }

    public int total() {
        if (!finish()) {
            return 0;
        }
        if(hasBonus() && bonusState.hasSecondPin()) {
            return state.getScoreCount() + bonusState.getFirstCount() + bonusState.getSecondCount();
        }
        if(hasBonus()) {
            return state.getScoreCount() + bonusState.getScoreCount();
        }
        return state.getScoreCount();
    }

    public int total(int beforTotal, int leftCount) {
        if (leftCount == 1 && hasSecondPin()) {
            return beforTotal + state.getScoreCount();
        }
        if (leftCount == 1 && hasBonus()) {
            return beforTotal + state.getScoreCount() + bonusState.getScoreCount();
        }
        if (leftCount == 0) {
            return beforTotal + firstCount();
        }
        return 0;
    }

    private boolean firstBonus() {
        return !hasBonus() && state.getBonusCount() > 0;
    }

    private boolean secondBonus() {
        return hasBonus() && !bonusState.stateFinish();
    }

    public boolean finish() {
        return (state.stateFinish() && state.scoreFinish()) ||
                (hasBonus() && bonusState.stateFinish());
    }

    @Override
    public boolean hasBonus() {
        return !ObjectUtils.isEmpty(bonusState);
    }

    @Override
    public int bonusFirstCount() {
        return bonusState.getFirstCount();
    }

    @Override
    public boolean hasBonusSecond() {
        return hasBonus() && bonusState.hasSecondPin();
    }

    @Override
    public int bonusSecondCount() {
        return bonusState.getSecondCount();
    }
}
