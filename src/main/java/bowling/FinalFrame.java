package bowling;

public class FinalFrame extends Frame {
    public FinalFrame(int order) {
        super(order);
    }

    @Override
    public int orderToThrow() {
        if (chances().areAllPinsDown()) {
            return -1;
        }
        return order();
    }

    @Override
    public void addChances(int knockDownCount) {
        if (chances().areAllPinsDown()) {
            return;
        }
        chances().add(knockDownCount);
    }

}
