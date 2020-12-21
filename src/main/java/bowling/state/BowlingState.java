package bowling.state;

import bowling.domain.score.BowlingScore;

import java.util.List;

/**
 * Created By mand2 on 2020-12-19.
 */
public interface BowlingState {

    boolean isPlayable();

    boolean isFinalPlayable();

    List<BowlingScore> getScoreBoard();

}
