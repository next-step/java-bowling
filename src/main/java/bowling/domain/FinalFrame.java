package bowling.domain;

public class FinalFrame extends Frame {

    @Override
    public boolean isEndCondition(int score) {
        return hasNotStrikeOrSpare() || hasSize(3);
    }

    private boolean hasNotStrikeOrSpare() {
        return hasSize(2) && this.scores.get(0) + scores.get(1) < FRAME_MAX_SCORE;
    }

    public boolean hasNotFinalFrame() {
        return this.hasSize(0);
    }

}
