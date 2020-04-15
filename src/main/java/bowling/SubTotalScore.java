package bowling;

public class SubTotalScore {

    private final int subTotalScore;
    private final NextAddingUpScores nextAddingUpScores;

    private SubTotalScore(final int subTotalScore, final NextAddingUpScores nextAddingUpScores) {
        this.subTotalScore = subTotalScore;
        this.nextAddingUpScores = nextAddingUpScores;
    }

    public static SubTotalScore newInstance(final int subTotalScore, final NextAddingUpScores nextAddingUpScores) {
        return new SubTotalScore(subTotalScore, nextAddingUpScores);
    }
}
