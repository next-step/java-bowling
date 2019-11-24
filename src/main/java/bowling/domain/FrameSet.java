package bowling.domain;

import bowling.domain.state.State;

public interface FrameSet {

    public void play(int hitCount);

    public FrameSet readyNext();

    public boolean isEnd();

    public FrameSet snapShot();

    public State getState();

    public int getPlayCount();
}
