package bowling.domain.frameresult;

public class Miss implements FrameResult {

    private final int firstNo;
    private final int secondNo;

    public Miss(int firstNo, int secondNo) {
        this.firstNo = firstNo;
        this.secondNo = secondNo;
    }

    @Override
    public int calculateScore() {
        return firstNo + secondNo;
    }

    @Override
    public boolean isCalculated() {
        return true;
    }

    @Override
    public void addBonus(int bonus) {
        throw new IllegalStateException();
    }
}
