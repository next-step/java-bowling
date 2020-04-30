package bowling.domain.shot.type;

class MissProvider implements StatusProvider {
    private final boolean isFirst;

    MissProvider(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public boolean isFinished() {
        return !isFirst;
    }

    public boolean isClear() {
        return false;
    }

    public int getBonusCount() {
        return 0;
    }
}
