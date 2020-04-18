package bowling.dto;

import java.util.Objects;

public class SubTotalConsoleResult {

    private final SubTotal subTotal;
    private final boolean canDisplay;

    private SubTotalConsoleResult(final SubTotal subTotal, final boolean canDisplay) {
        this.subTotal = subTotal;
        this.canDisplay = canDisplay;
    }

    public static SubTotalConsoleResult newInstance(final BowlingFrame bowlingFrame, final NextAddingUpScores nextAddingUpScores) {
        SubTotal subTotal = bowlingFrame.calculateSubTotal(nextAddingUpScores);
        return new SubTotalConsoleResult(subTotal, makeDisplayOption(bowlingFrame, nextAddingUpScores));
    }

    private static boolean makeDisplayOption(final BowlingFrame bowlingFrame, final NextAddingUpScores nextAddingUpScores) {
        if (!bowlingFrame.isOver()) {
            return false;
        }

        if (Objects.isNull(nextAddingUpScores)) {
            return true;
        }

        FrameScoreResult frameScoreResult = bowlingFrame.getResult();
        return frameScoreResult.canCalculateTotalScore(nextAddingUpScores);
    }

    public Integer sumTotalScore(final int totalScore) {
        return subTotal.getSubTotalScore() + totalScore;
    }

    public NextAddingUpScores getNextAddingUpScores() {
        return subTotal.getNextAddingUpScores();
    }

    public boolean canDisplay() {
        return canDisplay;
    }
}
