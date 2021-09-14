package bowling.domain.frame;

import bowling.domain.state.Bonus;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.exception.BowlingFrameException;
import org.springframework.util.ObjectUtils;

public class FinalFrame extends Frame {
    protected State bonusState;

    FinalFrame() {
        state = new Ready();
    }

    @Override
    public Frame next(int number) {
        if(!state.finish()){
            state = state.bowl(number);
            return this;
        }
        if(firstBonus()){
            bonusState = new Bonus(number, state.getScore().getBonusCount());
            return this;
        }
        if(secondBonus()){
            bonusState = bonusState.bowl(number);
            return this;
        }
        throw new BowlingFrameException("투구가 모두 종료되었습니다.");
    }

    private boolean firstBonus() {
        return !hasBonusFirst() && state.getScore().getBonusCount() > 0;
    }

    private boolean secondBonus() {
        return hasBonusFirst() && !bonusState.finish();
    }

    public boolean finish() {
        return (state.finish() && state.getScore().getBonusCount() == 0) ||
                (hasBonusFirst() && bonusState.finish());
    }

    @Override
    public boolean hasBonusFirst() {
        return !ObjectUtils.isEmpty(bonusState);
    }

    @Override
    public int bonusFirstCount() {
        return bonusState.getFirstPin().count();
    }

    @Override
    public boolean hasBonusSecond() {
        return hasBonusFirst() && !ObjectUtils.isEmpty(bonusState.getSecondPin());
    }

    @Override
    public int bonusSecondCount() {
        return bonusState.getSecondPin().count();
    }
}
