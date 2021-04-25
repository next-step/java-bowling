package bowling.domain.engine.roll;

class GutterResult extends RollResult {

    GutterResult() {
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
