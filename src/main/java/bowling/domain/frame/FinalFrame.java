package bowling.domain.frame;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.frameresult.Spare;
import bowling.domain.pin.FinalPinNumbers;

import java.util.Optional;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;

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
    public Frame nextFrame(int pinNo) {
        throw new IllegalStateException("current frame is final frame");
    }

    @Override
    public boolean canGetScore() {
        return pinNumbers.isFull();
    }

    @Override
    public Optional<Integer> score() {
        if (!canGetScore()) {
            return Optional.empty();
        }

        FrameResult result = pinNumbers.result();
        if (result instanceof Miss) {
            return result.score(null);
        }
        if (result instanceof Spare) {
            return Optional.of(MAX_PIN_NO + pinNumbers.getOwnSpareBonus());
        }
        return Optional.of(MAX_PIN_NO + pinNumbers.getOwnStrikeBonus());
    }

    private boolean isMiss() {
        return pinNumbers.result() instanceof Miss;
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
    public String expression() {
        return pinNumbers.expression();
    }
}
