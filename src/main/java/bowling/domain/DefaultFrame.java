package bowling.domain;

public class DefaultFrame extends Frame {

    private static final int DEFAULT_FRAME_SIZE = 2;

    @Override
    public boolean isRemainChance() {
        if (this.scores.isEmpty()) {
            return true;
        }
        return isRemainSecondChance();
    }

    private boolean isRemainSecondChance() {
        return (!this.scores.first().isStrike()) && (this.scores.size() < DEFAULT_FRAME_SIZE);
    }
}
