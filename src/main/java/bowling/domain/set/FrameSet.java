package bowling.domain.set;

import bowling.domain.state.State;

import java.util.List;

public interface FrameSet {

    public void play(int hitCount);

    public FrameSet next();

    public boolean isEndedFrame();

    public boolean isEndedGame();

    public FrameSet snapShot();

    public State getState();

    public int getPlayCount();

    public int getScore();

    public List<State> getHistory();
}
