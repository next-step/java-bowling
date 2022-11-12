package bowling;

public class NormalFrame extends Frame {
    private final Frame nextFrame;

    public NormalFrame(int order) {
        super(order);
        int nextOrder = order + 1;
        if(nextOrder == Frame.MAX_FRAME) {
            this.nextFrame = new FinalFrame(nextOrder);
            return;
        }
        this.nextFrame = new NormalFrame(nextOrder);
    }

    @Override
    public int orderToThrow() {
        if (chances().areAllPinsDown() || chances().noLeftChances()) {
            return nextFrame.orderToThrow();
        }
        return order();
    }

    @Override
    public void addChances(int knockDownCount) {
        if (chances().areAllPinsDown() || chances().noLeftChances()) {
            nextFrame.addChances(knockDownCount);
            return;
        }
        chances().add(knockDownCount);
    }

    public Frame nextFrame() {
        return nextFrame;
    }

}
