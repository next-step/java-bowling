package bowling.domain.state;

import bowling.domain.score.Score;

public interface LastState {
    public Score createScore();
}
