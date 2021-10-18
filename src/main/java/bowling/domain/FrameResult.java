package bowling.domain;

public class FrameResult {
    private final String indication;

    private final Score frameScore;

    private SumScore sumScore;

    public FrameResult(final String indication, final Score frameScore) {
        this.indication = indication;
        this.frameScore = frameScore;
        this.sumScore = SumScore.ZERO;
    }

    public String showIndication() {
        return indication;
    }

    public String showScore() {
        if (frameScore.canCalculate()) {
            return sumScore.toString();
        }
        return "";
    }

    public SumScore plusBeforeFrameSumScore(final SumScore beforeSumScore) {
        sumScore = beforeSumScore.plus(frameScore);
        return sumScore;
    }
}