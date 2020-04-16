package bowling;

public class SubTotal {

    private final TotalScore subTotalScore;
    private final NextAddingUpScores nextAddingUpScores;

    private SubTotal(final TotalScore subTotalScore, final NextAddingUpScores nextAddingUpScores) {
        this.subTotalScore = subTotalScore;
        this.nextAddingUpScores = nextAddingUpScores;
    }

    public static SubTotal newInstance(final int subTotalScore, final NextAddingUpScores nextAddingUpScores) {
        return new SubTotal(TotalScore.of(subTotalScore), nextAddingUpScores);
    }
}
