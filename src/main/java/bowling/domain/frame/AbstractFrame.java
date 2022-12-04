package bowling.domain.frame;

public abstract class AbstractFrame implements Frame {
    protected final int order;

    public AbstractFrame(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
