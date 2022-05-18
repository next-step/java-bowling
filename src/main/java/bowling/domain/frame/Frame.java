package bowling.domain.frame;

import java.util.Optional;

public interface Frame {

    void addPin(int pinNo);

    Frame getNextFrame(int pinNo);

    boolean canGetResult();


    Optional<Integer> getResult();

    int spareBonusForPreviousFrame();

    Optional<Integer> strikeBonusForPreviousFrame();
    String toExpression();
}
