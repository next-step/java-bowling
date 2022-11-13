package bowling.domain.frame;

import bowling.domain.Chances;

public abstract class Frame {
    public static final int MAX_FRAME = 10;
    public static final int NO_FRAME_TO_THROW = -1;

    private final int order;
    private final Chances chances;

    protected Frame(int order) {
        this.order = order;
        this.chances = new Chances();
    }

    public static Frame frame() {
        return new NormalFrame(1);
    }

    public abstract int orderToThrow();

    public abstract void addChances(int knockDownCount);

    public int order() {
        return order;
    }

    public Chances chances() {
        return chances;
    }


}
