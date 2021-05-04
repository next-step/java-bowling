package bowling.domain;

public final class FinalFrameOpportunity {

    private static final int START = 0;
    private static final int INCREASE_UNIT = 1;
    private static final int ORIGINAL_END = 2;
    private static final int BONUS_END = 3;

    private int opportunity;

    private FinalFrameOpportunity(final int opportunity) {
        this.opportunity = opportunity;
    }

    public static final FinalFrameOpportunity initialize() {
        return new FinalFrameOpportunity(START);
    }

    public final boolean isFinish(final boolean bonus) {
        if (opportunity >= BONUS_END || (!bonus && opportunity == ORIGINAL_END)) {
            return true;
        }
        return false;
    }

    public final void next() {
        opportunity = Math.addExact(opportunity, INCREASE_UNIT);
    }

}
