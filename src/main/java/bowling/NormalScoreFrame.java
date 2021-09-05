package bowling;

import java.util.List;
import java.util.Objects;

public class NormalScoreFrame implements ScoreFrame {
    private Turn turn;
    private FrameResult previousFrameResult = FrameResult.MISS;
    private Scores scores = new Scores();

    public NormalScoreFrame(Turn turn, FrameResult previousFrameResult) {
        this.turn = turn;
        this.previousFrameResult = previousFrameResult;
    }

    @Override
    public ScoreFrame process(int scoreValue) {
        scores.add(new Score(scoreValue));

        if (scores.isEnd()) {
            return getNextFrame(scores.getFrameResult());
        }

        return this;
    }

    @Override
    public int getTurnNumber() {
        return turn.getNumber();
    }

    @Override
    public String getScoreString() {
        return scores.getScoresString();
    }

    private ScoreFrame getNextFrame(FrameResult frameResult) {
        if (turn.isLastDoubleBallTurn()) {
            return new FinalScoreFrame(turn.getNextTurn());
        }

        return new NormalScoreFrame(turn.getNextTurn(), frameResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScoreFrame that = (NormalScoreFrame) o;
        return Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
