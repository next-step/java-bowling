package bowling.domain.frameresult;

import bowling.domain.frame.Frame;

import java.util.Optional;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;

public class Strike implements FrameResult {

    @Override
    public Optional<Integer> score(Frame nextFrame) {
        if (!canGetBonus(nextFrame)) {
            return Optional.empty();
        }
        return nextFrame.strikeBonusForPreviousFrame()
                .map(bonus -> MAX_PIN_NO + bonus);
    }

    private boolean canGetBonus(Frame nextFrame) {
        return nextFrame != null && nextFrame.strikeBonusForPreviousFrame().isPresent();
    }
}
