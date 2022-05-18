package bowling.domain.frameresult;

import java.util.Optional;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;

public class Strike implements FrameResult {

    @Override
    public Optional<Integer> calculateScore(Bonus bonus) {
        if (bonus.getStrikeBonus().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(MAX_PIN_NO + bonus.getStrikeBonus().get());
    }
}
