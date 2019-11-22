package bowling.domain;

public class FinalFrame extends Frame {

    @Override
    public boolean isEndCondition(int score) {
        return hasNotStrikeOrSpare() || hasSize(3);
    }

    private boolean hasNotStrikeOrSpare() {
        return hasSize(2) && this.scores.get(0) + scores.get(1) < FRAME_MAX_SCORE;
    }

    @Override
    public String getScoreFormat() {
        String format = String.format("%3s", findScoreFormat(0));

        if (hasSize(1)) {
            return format + "   ";
        }

        if (hasSize(2)) {
            return format + String.format("%s%-2s", "|", findSecondScoreFormat());
        }

        return String.format("%2s|%s|%s", findScoreFormat(0), findSecondScoreFormat(), findScoreFormat(2));
    }

    public boolean hasNotFinalFrame() {
        return this.hasSize(0);
    }

}
