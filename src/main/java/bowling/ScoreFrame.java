package bowling;


public abstract class ScoreFrame {
    final FrameMeta frameMeta;
    ScoreFrame next;

    public ScoreFrame(Turn turn) {
        this.frameMeta = new FrameMeta(turn);
    }

    abstract ScoreFrame addScore(int score);

    abstract boolean isCalculable();

    abstract Score getScore(Score previousScore);

    public int getTurnNumber() {
        return frameMeta.getTurnNumber();
    }

    public boolean isContinued() { return frameMeta.isLastTurn(); }

    public String getScoreString() {
        return frameMeta.getScoreString();
    }

    public int getCount() {
        return frameMeta.getCount();
    }

    public Score getSumScore(int n) {
        return frameMeta.getSumOfScore(n);
    }

    public boolean hasNextFrame() {
        return next != null;
    }

    public ScoreFrame getNextFrame() {
        return next;
    }
}
