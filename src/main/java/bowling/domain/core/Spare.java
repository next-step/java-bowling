package bowling.domain.core;

final class Spare extends AbstractSecondRolledResult {
    Spare(Pins first, Pins second) {
        super(first, second);
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinCount(0));
    }
}
