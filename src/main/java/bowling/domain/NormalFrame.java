package bowling.domain;

public class NormalFrame extends Frame {

    @Override
    public boolean isEndCondition(int score) {
        return this.scores.size() > 1 || score == FRAME_MAX_SCORE;
    }

    @Override
    public String getScoreFormat() {
        String format = String.format("%3s", findScoreFormat(0));

        if (this.hasSize(1)) {
            return format + "   ";
        }

        return format + String.format("%s%-2s", "|", findSecondScoreFormat());
    }

}
