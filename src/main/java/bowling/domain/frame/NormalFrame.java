package bowling.domain.frame;

import bowling.domain.Bowling;
import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.bowl.SecondBowl;
import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;

import java.util.Objects;
import java.util.Optional;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame implements Frame {
    private State state;
    private Frame next;

    @Override
    public Frame stroke(int frameNo, Pins pins) {
        Bowl firstBowl = new FirstBowl();
        this.state = firstBowl.stroke(pins);

        if (this.state.isFinished()) {
            return next(frameNo);
        }
        return this;
    }

    private Frame next(int frameNo) {
        if (frameNo + Bowling.INDEX_ONE == Bowling.FINAL_FRAME) {
            this.next = new FinalFrame();
            return this.next;
        }
        this.next = new NormalFrame();
        return this.next;
    }

    @Override
    public Frame spare(int frameNo, Pins pins) {
        Bowl secondBowl = new SecondBowl(state);
        this.state = secondBowl.stroke(pins);
        return next(frameNo);
    }

    @Override
    public State getState() {
        return Optional.ofNullable(this.state)
                .orElse(new None());
    }

    @Override
    public String getSymbol() {
        return Optional.ofNullable(this.state)
                .map(State::getSymbol)
                .orElse("");
    }

    @Override
    public Frame getNext() {
        return Optional.ofNullable(this.next)
                .orElse(new NormalFrame());
    }

    @Override
    public int getFrameScore() {
        return Optional.ofNullable(this.state)
                .map(State::getScore)
                .map(Score::getFrameScore)
                .orElse(0);
    }

    @Override
    public int getFirstScore() {
        return Optional.ofNullable(this.state)
                .map(State::getScore)
                .map(Score::getFirst)
                .map(Pins::get)
                .orElse(0);
    }

    @Override
    public int getSecondScore() {
        return Optional.ofNullable(this.state)
                .map(State::getScore)
                .map(Score::getSecond)
                .map(Pins::get)
                .orElse(0);
    }

    @Override
    public boolean isVisible() {
        State nextNext = Optional.ofNullable(this.next)
                .map(Frame::getNext)
                .map(Frame::getState)
                .orElse(new None());

        if (next == null) {
            return false;
        }

        if (getState().isFinished()
                && !(getState() instanceof Strike)
                && !(getState() instanceof Spare)) {
            return true;
        }
        if (getState().isFinished()
                && !(getState() instanceof Strike)
                && next.getState() instanceof Miss) {
            return true;
        }

        if (next.getState() instanceof FinalSecondState
                || next.getState() instanceof FinalThirdState) {
            return true;
        }
        if (next.getState() instanceof Spare) {
            return true;
        }

        if (nextNext instanceof Strike) {
            return true;
        }
        if (nextNext instanceof FinalFirstState
                || nextNext instanceof FinalSecondState
                || nextNext instanceof FinalThirdState) {
            return true;
        }
        if (nextNext instanceof Miss
                || nextNext instanceof Gutter) {
            return true;
        }

        return false;
    }

    @Override
    public int getBonusScore() {
        if (this.state instanceof Strike) {
            return next.getFrameScore();
        }
        if (this.state instanceof Spare) {
            return next.getFirstScore();
        }

        if (this.next instanceof FinalFrame) {
            return this.next.getBonusScore();
        }
        return 0;
    }

    @Override
    public int getBonus2Score() {
        Frame next2 = Optional.ofNullable(this.next).orElse(new NormalFrame());
        Frame nextNext = Optional.ofNullable(next2.getNext()).orElse(new NormalFrame());

        if (next2.getState() instanceof Strike) {
            return nextNext.getFirstScore();
        }

        if (this.next instanceof FinalFrame) {
            return this.next.getBonusScore();
        }
        if (nextNext instanceof FinalFrame) {
            return nextNext.getBonusScore();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final NormalFrame other = (NormalFrame) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
