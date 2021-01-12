package bowling.domain.frame;

public abstract class Frame {

    public abstract void record(int downedPin);

    public abstract boolean isEnd();
}
