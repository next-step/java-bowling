package bowling.domain.engine.frame;

import bowling.domain.engine.roll.RollResult;
import bowling.dto.Exportable;

public interface Frame extends Exportable<String> {

    void roll(RollResult rollResult);

    boolean isEnded();

    String export();

}
