package bowling.state;

import bowling.domain.score.ScoreResult;


/**
 * Created By mand2 on 2020-12-19.
 */
public interface BowlingState {

    boolean isPlayable();

    boolean isFinalPlayable();

    ScoreResult getScoreBoard();

}
