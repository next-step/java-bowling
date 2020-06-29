package bowling.domain;

public class FinalFrame {

    private final Score firstPitchingScore;
    private final Score secondPitchingScore;
    private final Score finalPitchingScore;

    private FinalFrame(Score firstPitchingScore, Score secondPitchingScore, Score finalPitchingScore) {
        this.firstPitchingScore = firstPitchingScore;
        this.secondPitchingScore = secondPitchingScore;
        this.finalPitchingScore = finalPitchingScore;
    }

    public static FinalFrame of(Score firstPitchingScore, Score secondPitchingScore, Score finalPitchingScore) {
        return new FinalFrame(firstPitchingScore, secondPitchingScore, finalPitchingScore);
    }
}
