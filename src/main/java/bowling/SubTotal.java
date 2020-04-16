package bowling;

import java.util.Objects;

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

    public static SubTotal newInstance(final TotalScore subTotalScore, final NextAddingUpScores nextAddingUpScores) {
        return new SubTotal(subTotalScore, nextAddingUpScores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTotal)) return false;
        SubTotal subTotal = (SubTotal) o;
        return Objects.equals(subTotalScore, subTotal.subTotalScore) &&
                Objects.equals(nextAddingUpScores, subTotal.nextAddingUpScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subTotalScore, nextAddingUpScores);
    }
}
