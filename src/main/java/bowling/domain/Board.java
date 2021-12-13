package bowling.domain;

import bowling.domain.frame.Round;
import bowling.domain.result.GameResult;

import java.util.List;
import java.util.Objects;

public class Board {
    private final List<Round> allRounds = Round.allRounds();
    private final GameResult gameResult;

    public Board(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(allRounds, board.allRounds) && Objects.equals(gameResult, board.gameResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allRounds, gameResult);
    }

    @Override
    public String toString() {
        return "Board{" +
                ", gameResult=" + gameResult +
                '}';
    }
}
