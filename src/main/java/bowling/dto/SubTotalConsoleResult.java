package bowling.dto;

import bowling.BowlingFrame;
import bowling.FrameScoreResult;
import bowling.NextAddingUpScores;
import bowling.SubTotal;

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
        FrameScoreResult frameScoreResult = bowlingFrame.getResult();
        return new SubTotalConsoleResult(subTotal, makeDisplayOption(subTotal.getNextAddingUpScores(), frameScoreResult));
    }

    private static boolean makeDisplayOption(final NextAddingUpScores nextAddingUpScores, final FrameScoreResult frameScoreResult) {
        if (Objects.isNull(nextAddingUpScores)) {
            return true;
        }

        System.out.println(nextAddingUpScores);
        return true;

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
