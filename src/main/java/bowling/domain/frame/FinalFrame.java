package bowling.domain.frame;

import bowling.domain.pin.FinalExtraPins;
import bowling.domain.pin.Pin;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class FinalFrame implements Frame {

    private static final int FINAL_FRAME_NO = 10;

    private State state;

    private final FinalExtraPins extraPins;

    public FinalFrame() {
        this(new Ready());
    }

    public FinalFrame(State state) {
        this.state = state;
        this.extraPins = new FinalExtraPins();
    }

    @Override
    public int number() {
        return FINAL_FRAME_NO;
    }

    @Override
    public Frame bowl(Pin no) {
        if (state.finished()) {
            addExtraPin(no);
            return this;
        }
        state = state.bowl(no);
        return this;
    }

    private void addExtraPin(Pin no) {
        if (state instanceof Spare) {
            extraPins.addSpareExtra(no);
        }
        if (state instanceof Strike) {
            extraPins.addStrikeExtra(no);
        }
    }

    @Override
    public Score score() {
        Score score = state.score();
        if (score.canGetScore()) {
            return score;
        }
        if (state instanceof Spare) {
            return extraPins.spareScore(score);
        }
        return extraPins.strikeScore(score);
    }

    @Override
    public boolean finished() {
        return state.finished() && score().canGetScore();
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        Score afterScore = state.additionalScore(beforeScore);
        if (afterScore.canGetScore()) {
            return afterScore;
        }
        return extraPins.spareScore(afterScore);
    }

    @Override
    public String expression() {
        return extraPins.expression(state);
    }
}
