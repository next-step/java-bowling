package bowling.domain.frame;

public class Rollings extends AbstractRollings {

    private Rollings(Rolling first, Rolling second) {
        super(first, second);
    }

    @Override
    protected void validateRollings(Rolling first, Rolling second) {
        RollingsValidation rollingsValidation = RollingsValidation.of(first, second);
        if (rollingsValidation != RollingsValidation.NONE) {
            throw new RollingsException(rollingsValidation.message());
        }
    }

    public static Rollings first(int first) {
        return new Rollings(new Rolling(first), null);
    }

    public Rollings second(int second) {
        return new Rollings(first(), new Rolling(second));
    }

    @Override
    public boolean allRolled() {
        if (isStrike() || isSpare()) {
            return true;
        }
        return !isStrike() && second() != null;
    }
}
