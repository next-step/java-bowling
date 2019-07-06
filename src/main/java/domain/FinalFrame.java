package domain;

public class FinalFrame {
    private FinalScore finalScore;

    public FinalFrame() {
        finalScore = new FinalScore();
    }

    public boolean doBowling(int point) {
        return finalScore.bowl(point);
    }

    public String getResult() {
        return finalScore.getResult();
    }

    public int sumScore() {
        return finalScore.sumScore();
    }

    public boolean isGameOver() {
        return !finalScore.nowBowlable();
    }

    public int getPointSize() {
        return finalScore.getPointSize();
    }
}
