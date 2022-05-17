package bowling.domain.frame;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Spare;
import bowling.domain.frameresult.Strike;
import bowling.domain.pin.FinalPinNumbers;

import java.util.Optional;

public class FinalFrame implements Frame {

    private final FinalPinNumbers pinNumbers;

    FinalFrame(int pinNo) {
        pinNumbers = new FinalPinNumbers(pinNo);
    }

    @Override
    public boolean canGetResult() {
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
    public FrameResult getResult() {
        FrameResult result = pinNumbers.getResult();
        if (result instanceof Spare) {
            result.addBonus(pinNumbers.getOwnSpareBonus());
        }
        if (result instanceof Strike) {
            result.addBonus(pinNumbers.getOwnStrikeBonus());
        }
        return result;
    }

    @Override
    public int spareBonusForPreviousFrame() {
        return pinNumbers.spareBonus();
    }

    @Override
    public Optional<Integer> strikeBonusForPreviousFrame() {
        if (pinNumbers.size() < FinalPinNumbers.MIN_SIZE) {
            return Optional.empty();
        }
        return Optional.of(pinNumbers.strikeBonus());
    }
}
