package bowling.domain;

import bowling.domain.frame.Round;
import bowling.domain.result.GameResults;

import java.util.List;
import java.util.Objects;

public class Board {
    private final List<Round> allRounds = Round.allRounds();
    private final GameResults gameResults;

    public Board(GameResults gameResults) {
        this.gameResults = gameResults;
    }

    public List<Round> getAllRounds() {
        return allRounds;
    }

    public GameResults getGameResults() {
        return gameResults;
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
        return Objects.equals(allRounds, board.allRounds) && Objects.equals(gameResults, board.gameResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allRounds, gameResults);
    }

    @Override
    public String toString() {
        return "Board{" +
                ", gameResults=" + gameResults +
                '}';
    }
}
