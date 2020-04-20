package bowling.domain.state;

import bowling.domain.Score;

public interface State  {
    State bowl (Score score);

    boolean isFinish();

    String display();

}
