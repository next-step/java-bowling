package bowling;

public class Frame {
    private final Score score;
    private Status status;

    public Frame(Score score) {
        this.score = score;
        this.status = this.score.createFrameStatus();
    }

    public int getScore() {
        return score.getScore();
    }

    public Status getStatus() {
        return status;
    }
}
