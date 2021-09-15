package bowling.domain.frame;

public abstract class Frame {

    protected int trial;

    protected Frame(int trial) {
        this.trial = trial;
    }

    public int getTrial() {
        return trial;
    }

    protected abstract boolean isLast();
}
