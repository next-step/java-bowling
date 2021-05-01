package bowling.domain;

public final class FinalFrameOpportunity {

    private static final int START = 0;
    private static final int END = 3;

    private final int opportunity;

    private FinalFrameOpportunity(final int opportunity) {
        this.opportunity = opportunity;
    }

    public static final FinalFrameOpportunity initialize() {
        return new FinalFrameOpportunity(START);
    }

}
