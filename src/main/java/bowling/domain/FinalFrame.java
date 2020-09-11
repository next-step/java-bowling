package bowling.domain;

public class FinalFrame implements Frame {

    public static FinalFrame from(Frame previous) {
        return null;
    }

    @Override
    public BowlResult bowl(int value) {
        return null;
    }

    @Override
    public boolean canBowl() {
        return false;
    }

}
