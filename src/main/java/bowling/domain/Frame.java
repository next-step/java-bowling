package bowling.domain;

/**
 * 프레임은 점수를 기록하고 다음 프레임을 생성하는 책임을 가진다.
 */
public class Frame {
    private Score score;
    private int frameIndex;

    public Frame() {
    }

    private Frame(int frameIndex) {
        this.score = new Score();
        this.frameIndex = frameIndex;
    }

    public static Frame first() {
        return new Frame(1);
    }

    public Frame nextFrame() {
        return new Frame(frameIndex + 1);
    }

    public void addScore(int score) {
        this.score.add(score);
    }

    public int getScore() {
        return score.getTotalScore();
    }
}
