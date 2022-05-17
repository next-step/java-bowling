package bowling.domain.frame;

import bowling.domain.frameresult.FrameResult;

import java.util.Optional;

public interface Frame {

    boolean canGetResult();

    void addPin(int pinNo);

    Frame getNextFrame(int pinNo);

    String toExpression();

    FrameResult getResult();

    int spareBonusForPreviousFrame();

    Optional<Integer> strikeBonusForPreviousFrame();
}
