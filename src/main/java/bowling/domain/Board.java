package bowling.domain;

import bowling.domain.frame.Round;
import bowling.domain.result.GameResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    public static final String EMPTY_FRAME_RESULT_STRING = "";
    private static final int ROUND_MAX_SIZE = 10;
    private final List<Round> allRounds = Round.allRounds();
    private final GameResult gameResult;

    public Board(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public Integer[] allRoundToIntArray() {
        return allRounds.stream()
                .map(Round::value)
                .collect(Collectors.toList())
                .toArray(new Integer[ROUND_MAX_SIZE]);
    }

    public String[] gameResultToStringArray() {
        String[] results = new String[ROUND_MAX_SIZE];
        for (int i = 0; i < ROUND_MAX_SIZE; i++) {
            results[i] = gameResult.findFrameViewStringWithIndex(i).orElse(EMPTY_FRAME_RESULT_STRING);
        }
        return results;
    }

    public String userName() {
        return gameResult.userName();
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
