package bowling.domain;

public class FrameResult {
    private final PointSymbols pointSymbols;

    public FrameResult(PointSymbols pointSymbols) {
        this.pointSymbols = pointSymbols;
    }

    public PointSymbols pointSymbols() {
        return pointSymbols;
    }
}
