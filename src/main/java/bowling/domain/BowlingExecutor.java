package bowling.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 18:52
 */
public class BowlingExecutor {
    private GameBoard board;

    public BowlingExecutor() {
        this.board = new GameBoard();
    }

    public List<List<Integer>> execute(int fallCount) {
        return convert(board.play(fallCount));
    }

    private List<List<Integer>> convert(Map<Integer, FrameScore> soucre) {
        return soucre.keySet().stream()
                .map(key -> soucre.get(key))
                .map(frameScore -> frameScore.getScoreNumber())
                .collect(Collectors.toList());
    }
}
