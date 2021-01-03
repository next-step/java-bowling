package bowling.domain.frame;

import bowling.domain.Symbol;
import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FinalFirstBowl;
import bowling.domain.bowl.FinalSecondBowl;
import bowling.domain.bowl.FinalThirdBowl;
import bowling.domain.score.Pins;
import bowling.domain.state.*;

import java.util.Objects;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame implements Frame {
    private State firstState;
    private State secondState;
    private State thirdState;

    @Override
    public Frame stroke(int frameNo, Pins pins) {
        Bowl finalFirstBowl = new FinalFirstBowl();
        this.firstState = finalFirstBowl.stroke(pins);
        return this;
    }

    @Override
    public Frame spare(int frameNo, Pins pins) {
        if (isBonus(firstState, secondState)) {
            FinalThirdBowl finalThirdBowl = new FinalThirdBowl(firstState, secondState);
            this.thirdState = finalThirdBowl.stroke(pins);
            return this;
        }
        Bowl finalSecondBowl = new FinalSecondBowl(this.firstState);
        this.secondState = finalSecondBowl.stroke(pins);
        return this;
    }

    private boolean isBonus(State firstState, State secondState) {
        return secondState != null
                && (firstState.getSymbol().contains(Symbol.STRIKE.getSymbol())
                || secondState.getSymbol().contains(Symbol.STRIKE.getSymbol())
                || secondState.getSymbol().contains(Symbol.SPARE.getSymbol()));
    }

    @Override
    public State getState() {
        if (this.firstState == null) {
            return new None();
        }

        if (this.secondState == null) {
            return this.firstState;
        }
        if (this.thirdState == null) {
            return this.secondState;
        }
        return this.thirdState;
    }

    @Override
    public String getSymbol() {
        if (this.secondState == null) {
            return this.firstState.getSymbol();
        }
        if (this.thirdState == null) {
            return this.secondState.getSymbol();
        }
        return this.thirdState.getSymbol();
    }

    @Override
    public Frame getNext() {
        return new FinalFrame();
    }

    @Override
    public int getFrameScore() {
        if (this.firstState == null) return 0;
        if (this.secondState == null) {
            return this.firstState.getScore().getFrameScore();
        }
        if (this.thirdState == null) {
            return this.secondState.getScore().getFrameScore();
        }
        return this.thirdState.getScore().getFrameScore();
    }

    @Override
    public int getFirstScore() {
        if (this.firstState == null) return 0;
        if (this.secondState == null) {
            return this.firstState.getScore().getFirst().get();
        }
        if (this.thirdState == null) {
            return this.secondState.getScore().getFirst().get();
        }
        return this.thirdState.getScore().getFirst().get();
    }

    @Override
    public int getSecondScore() {
        if (this.firstState == null) return 0;
        if (this.secondState == null) {
            return this.firstState.getScore().getSecond().get();
        }
        if (this.thirdState == null) {
            return this.secondState.getScore().getSecond().get();
        }
        return this.thirdState.getScore().getSecond().get();
    }

    @Override
    public boolean isVisible() {
        return thirdState != null;
    }

    @Override
    public int getBonusScore() {
        if (this.firstState == null) {
            return 0;
        }
        return this.firstState.getScore().getFrameScore() + this.secondState.getScore().getFrameScore();
    }

    @Override
    public int getBonus2Score() {
        if (this.firstState == null) {
            return 0;
        }
        if (this.secondState == null) {
            return this.firstState.getScore().getFrameScore();
        }
        if (this.thirdState == null) {
            return this.firstState.getScore().getFrameScore() + this.secondState.getScore().getFrameScore();
        }
        return this.firstState.getScore().getFrameScore() + this.secondState.getScore().getFrameScore() + this.thirdState.getScore().getFrameScore();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final FinalFrame other = (FinalFrame) obj;
        if (!Objects.equals(this.firstState, other.firstState)) {
            return false;
        }
        if (!Objects.equals(this.secondState, other.secondState)) {
            return false;
        }
        if (!Objects.equals(this.thirdState, other.thirdState)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
