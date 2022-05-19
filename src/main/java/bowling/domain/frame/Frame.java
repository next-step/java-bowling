package bowling.domain.frame;

import java.util.Optional;

public interface Frame {

    void addPin(int pinNo);

    Frame nextFrame(int pinNo);

    boolean canGetScore();

    Optional<Integer> score();

    int spareBonusForPreviousFrame();

    Optional<Integer> strikeBonusForPreviousFrame();

    String expression();
}
