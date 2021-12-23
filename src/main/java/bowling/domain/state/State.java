package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.frame.Frame;

public interface State {

    State run(Pitch pitch);

    Score score();

    Score calculateBonusScore(Score beforeScore);

    String symbol();

    boolean isMiss();

    boolean isBonus();

    boolean isEnd();}
