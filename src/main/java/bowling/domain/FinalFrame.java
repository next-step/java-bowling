package bowling.domain;

public class FinalFrame implements Frame {
    private BowlResult bowlResult;
    private int left;

    private FinalFrame() {
        this(null, 0);
    }


    private FinalFrame(BowlResult bowlResult, int left) {
        this.bowlResult = bowlResult;
        this.left = left;
    }

    public static Frame init() {
        return new FinalFrame();
    }

    public static Frame firstBowl(int downPin, int left) {
        BowlResult bowlResult = BowlResult.of(downPin);
        if (bowlResult.isStrike(downPin)) {
            left = 1;
        }
        return new FinalFrame(bowlResult, --left);
    }


    @Override
    public Frame nextBowl(int downPin) {
        bowlResult = bowlResult.next(downPin);
        return new FinalFrame(bowlResult, --left);
    }

    @Override
    public boolean isLast() {
        return left == 0;
    }
}
