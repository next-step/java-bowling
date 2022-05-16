package bowling.domain.frameresult;

public class Miss implements FrameResult {

    private final int firstNo;
    private final int secondNo;

    public Miss(int firstNo, int secondNo) {
        this.firstNo = firstNo;
        this.secondNo = secondNo;
    }

    @Override
    public int getScoreWithBonus(int bonus) {
        return firstNo + secondNo;
    }
}
