package bowling.domain.core;

final class NotAtRolledResult implements RolledResult {
    @Override
    public boolean isCompleteState() {
        return false;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return 0;
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return ImmutableTwoFallenPins.gutter();
    }
}
