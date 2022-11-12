package bowling;

public class NormalFrame extends Frame {
    private final Frame nextFrame;

    public NormalFrame(int order) {
        super(order);
        if(order + 1 == Frame.MAX_FRAME) {
            this.nextFrame = new FinalFrame(order + 1);
            return;
        }
        this.nextFrame = new NormalFrame(order + 1);
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
