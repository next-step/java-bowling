package camp.nextstep.edu.rebellion.bowling.domain.score;

import java.util.Collections;
import java.util.List;

public class BowlingGameScoreBoard {
    private final List<PersonalScoreBoard> boards;

    public BowlingGameScoreBoard(List<PersonalScoreBoard> boards) {
        this.boards = boards;
    }

    public List<PersonalScoreBoard> getPersonalScoreBoards() {
        return Collections.unmodifiableList(boards);
    }
}
