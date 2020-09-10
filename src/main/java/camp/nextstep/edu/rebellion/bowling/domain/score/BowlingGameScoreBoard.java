package camp.nextstep.edu.rebellion.bowling.domain.score;

import java.util.Collections;
import java.util.List;

public class BowlingGameScoreBoard {
    private static final int DEFAULT_COUNT_OF_FRAMES = 10;

    private final List<PersonalScoreBoard> boards;

    public BowlingGameScoreBoard(List<PersonalScoreBoard> boards) {
        this.boards = boards;
    }

    public List<PersonalScoreBoard> getPersonalScoreBoards() {
        return Collections.unmodifiableList(boards);
    }

    public int getTotalFrames() {
        return boards.stream()
                .map(b -> b.getResultSymbol().size())
                .max(Integer::compareTo)
                .orElse(DEFAULT_COUNT_OF_FRAMES);
    }
}
