package bowling;

import java.util.Objects;

public abstract class ScoreFrame {
    final Turn turn;
    final Trial trial = new Trial();

    public ScoreFrame(Turn turn) {
        this.turn = turn;
    }

    abstract ScoreFrame addScore(int score);

    public int getTurnNumber() {
        return turn.getNumber();
    }

    public String getScoreString() {
        return trial.getScoreString();
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
