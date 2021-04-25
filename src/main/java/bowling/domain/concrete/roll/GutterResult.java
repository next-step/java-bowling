package bowling.domain.concrete.roll;

import bowling.domain.engine.roll.RollResult;

public class GutterResult extends RollResult {

    public GutterResult() {
        super(0);
    }

    @Override
    public boolean isClear() {
        return false;
    }

    @Override
    public String export() {
        return "-";
    }
}
