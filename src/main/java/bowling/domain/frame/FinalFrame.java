package bowling.domain.frame;

public class FinalFrame extends Frame {
    public static final int NO_FRAME_TO_THROW = -1;

    public FinalFrame(int order) {
        super(order);
    }

    @Override
    public int orderToThrow() {
        if (chances().noLeftChances(true)) {
            return NO_FRAME_TO_THROW;
        }
        return order();
    }

    @Override
    public void addChances(int knockDownCount) {
        if (chances().noLeftChances(true)) {
            return;
        }
        chances().add(knockDownCount);
    }

}
