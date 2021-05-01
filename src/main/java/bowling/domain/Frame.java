package bowling.domain;

public abstract class Frame {
    protected final Score score;
    protected int availability;

    protected Frame(Score score) {
        this.score = new Score(score);
    }

    protected Frame() {
        score = new Score();
    }

    public abstract String addScore(int inputScore);

    public abstract Frame createFrame(int frameNumber);

    public boolean isAvailable() {
        if (availability > 0) {
            return true;
        }
        return false;
    }

    public String getFormattedScore(int lastScore) {
        if (lastScore == 0) {
            return "-";
        }
        if (lastScore == 10) {
            return "X";
        }
        if (score.isPinCleared()) {
            return "/";
        }
        return String.valueOf(lastScore);
    }
}
