package bowling.domain;

public class DefaultFrame extends Frame {

    @Override
    public boolean isRemainChance() {
        if (this.scores.isEmpty()) {
            return true;
        }
        return isRemainSecondChance();
    }

    private boolean isRemainSecondChance() {
        return (!this.scores.first().isStrike()) && (this.scores.size() < 2);
    }
}
