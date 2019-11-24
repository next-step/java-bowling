package bowling.domain.set;

import bowling.domain.state.State;

public interface FrameSet {

    public void play(int hitCount);

    public FrameSet next();

    public boolean isEnd();

    public FrameSet snapShot();

    public State getState();

    public int getPlayCount();
}
