package bowling;

public abstract class Frame {
    public static final int MAX_FRAME = 10;

    private final int order;
    private final Chances chances = new Chances();

    protected Frame(int order) {
        this.order = order;
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
