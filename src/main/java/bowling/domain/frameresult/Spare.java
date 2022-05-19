package bowling.domain.frameresult;

import bowling.domain.frame.Frame;

import java.util.Optional;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;

public class Spare implements FrameResult {

    @Override
    public Optional<Integer> score(Frame nextFrame) {
        if (nextFrame == null) {
            return Optional.empty();
        }
        return Optional.of(MAX_PIN_NO + nextFrame.spareBonusForPreviousFrame());
    }
}
