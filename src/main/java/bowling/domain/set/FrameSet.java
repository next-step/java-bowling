package bowling.domain.set;

import bowling.domain.History;
import bowling.domain.score.Score;
import bowling.domain.state.State;

public interface FrameSet {

    public void play(int hitCount);

    public FrameSet getNext();

    public boolean isEndedFrame();

    public boolean isEndedGame();

    public FrameSet snapShot();

    public State getState();

    public int getPlayCount();

    public int getTotalScore();

    public History getHistory();

    public int calculateAdditionalScore(Score score);

    public boolean canCalculateScore();
}
