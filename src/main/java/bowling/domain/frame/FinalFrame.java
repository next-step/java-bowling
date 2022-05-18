package bowling.domain.frame;

import bowling.domain.frameresult.Bonus;
import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.pin.FinalPinNumbers;

import java.util.Optional;

public class FinalFrame implements Frame {

    private final FinalPinNumbers pinNumbers;

    FinalFrame(int pinNo) {
        pinNumbers = new FinalPinNumbers(pinNo);
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
    public boolean canGetResult() {
        return pinNumbers.isFull();
    }

    @Override
    public Optional<Integer> getResult() {
        FrameResult result = pinNumbers.getResult();
        Bonus bonus = new Bonus();
        if (!isMiss()) {
            bonus.setSpareBonus(pinNumbers.getOwnSpareBonus());
            bonus.setStrikeBonus(pinNumbers.getOwnStrikeBonus());
        }
        return result.calculateScore(bonus);
    }

    private boolean isMiss() {
        return pinNumbers.getResult() instanceof Miss;
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

    @Override
    public String toExpression() {
        return pinNumbers.toExpression();
    }
}
