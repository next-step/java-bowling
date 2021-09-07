package bowling.domain.frame;

public class NormalRollings extends Rollings {

    private NormalRollings(Rolling first, Rolling second) {
        super(first, second);
    }

    @Override
    protected void validateRollings(Rolling first, Rolling second) {
        NormalRollingsValidation normalRollingsValidation = NormalRollingsValidation.of(first, second);
        if (normalRollingsValidation != NormalRollingsValidation.NONE) {
            throw new NormalRollingsException(normalRollingsValidation.message());
        }
    }

    public static NormalRollings first(int first) {
        return new NormalRollings(new Rolling(first), null);
    }

    public NormalRollings second(int second) {
        return new NormalRollings(first(), new Rolling(second));
    }

    @Override
    public Rolling third() {
        return null;
    }

    @Override
    public boolean allRolled() {
        if (isStrike() || isSpare()) {
            return true;
        }
        return !isStrike() && second() != null;
    }
}
