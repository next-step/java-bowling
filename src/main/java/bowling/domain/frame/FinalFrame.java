package bowling.domain.frame;

public class FinalFrame {

    private final FinalRollings finalRollings;

    public FinalFrame(FinalRollings finalRollings) {
        this.finalRollings = finalRollings;
    }

    public FinalFrame(int first) {
        this(FinalRollings.first(first));
    }

    public FinalFrame roll(int fallenPin) {
        return new FinalFrame(finalRollings.roll(fallenPin));
    }

    public boolean allRolled() {
        return finalRollings.allRolled();
    }
}
