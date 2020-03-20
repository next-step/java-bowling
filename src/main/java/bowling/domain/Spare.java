package bowling.domain;

public class Spare implements FrameStatus {

    private int preScore;
    private int score;

    public Spare(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
    }
}
