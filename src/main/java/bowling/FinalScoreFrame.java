package bowling;

import java.util.Objects;

public class FinalScoreFrame implements ScoreFrame {
    private Turn turn;
    private Score firstScore;
    private Score secondScore;
    private Score thirdScore;

    private Scores scores = new Scores();

    public FinalScoreFrame(Turn turn) {
        this.turn = turn;
    }

    @Override
    public ScoreFrame process(int score) {
        return null;
    }

    @Override
    public int getTurnNumber() {
        return turn.getNumber();
    }

    @Override
    public String getScoreString() {
        return scores.getScoresString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalScoreFrame that = (FinalScoreFrame) o;
        return Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
