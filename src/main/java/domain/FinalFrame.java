package domain;

public class FinalFrame {
    private FinalScore finalScore;

    public FinalFrame() {
        finalScore = new FinalScore();
    }

    public int doBowling(int point) {
        return finalScore.bowl(point);
    }

    public String getScore() {
        return finalScore.getScore();
    }
}
