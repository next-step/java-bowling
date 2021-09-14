package bowling;

public class FrameMeta {
    private final Turn turn;
    private final Trial trial = new Trial();

    public FrameMeta(Turn turn) {
        this.turn = turn;
    }

    public boolean isLastNormalTurn() {
        return turn.isLastNormalTurn();
    }

    public Turn getNextTurn() {
        return turn.getNextTurn();
    }

    public int getTurnNumber() {
        return turn.getNumber();
    }

    public boolean isLastTurn() {
        return turn.isLastTurn();
    }

    public void add(Score score) {
        trial.add(score);
    }

    public boolean isNormalEnd() {
        return trial.isNormalEnd();
    }

    public boolean isFinalEnd() {
        return trial.isFinalEnd();
    }

    public String getScoreString() {
        return trial.getScoreString();
    }

    public int getCount() {
        return trial.getCount();
    }

    public FrameResult getFrameResult() {
        return trial.getFrameResult();
    }

    public Score getSumOfScore(int numberOf) {
        return trial.getSumScore(numberOf);
    }

    public Score getTotalSumScore() {
        int total = trial.getCount();

        return trial.getSumScore(total);
    }
}
