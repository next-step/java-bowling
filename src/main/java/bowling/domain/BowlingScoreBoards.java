package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

import bowling.engine.ScoreBoard;
import bowling.engine.ScoreBoards;
import bowling.engine.collection.FirstClassImmutableList;

public class BowlingScoreBoards extends FirstClassImmutableList<ScoreBoard> implements ScoreBoards {

    protected BowlingScoreBoards(List<ScoreBoard> collection) {
        super(collection);
    }

    public static BowlingScoreBoards of(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("names cannot be null or empty");
        }

        List<ScoreBoard> boards = names.stream()
                .map(BowlingScoreBoard::of)
                .collect(Collectors.toList());

        return new BowlingScoreBoards(boards);
    }

    static ScoreBoards fromBoard(List<ScoreBoard> boards) {
        return new BowlingScoreBoards(boards);
    }

    @Override
    public ScoreBoard first() {
        return  collection.iterator()
                .next();
    }

    @Override
    public ScoreBoard next(ScoreBoard previous) {
        if (previous.last()
                .result()
                .completed()) {
            return nextOfRing(previous);
        }

        return previous;
    }

    @Override
    public boolean isEnded() {
        return stream().allMatch(ScoreBoard::isEnded);
    }
}
