package bowling.domain.frame;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.frameresult.Spare;
import bowling.domain.pin.FinalPinNumbers;

public class FinalFrame implements Frame {

    private final FinalPinNumbers pinNumbers;

    FinalFrame(int pinNo) {
        pinNumbers = new FinalPinNumbers(pinNo);
    }

    @Override
    public boolean isFull() {
        return pinNumbers.isFull();
    }

    @Override
    public void addPin(int pinNo) {
        pinNumbers.addPin(pinNo);
    }

    @Override
    public Frame getNextFrame(int pinNo) {
        throw new IllegalStateException("current frame is final frame");
    }

    @Override
    public String toExpression() {
        return pinNumbers.toExpression();
    }

    @Override
    public boolean canGetScore() {
        return isFull();
    }

    @Override
    public int getScore() {
        FrameResult result = pinNumbers.getResult();
        if (result instanceof Miss) {
            return result.getScoreWithBonus(0);
        }
        if (result instanceof Spare) {
            return result.getScoreWithBonus(pinNumbers.getOwnSpareBonus());
        }
        return result.getScoreWithBonus(pinNumbers.getOwnStrikeBonus());
    }

    @Override
    public int spareBonusForPreviousFrame() {
        return pinNumbers.spareBonus();
    }

    @Override
    public int strikeBonusForPreviousFrame() {
        return pinNumbers.strikeBonus();
    }
}
