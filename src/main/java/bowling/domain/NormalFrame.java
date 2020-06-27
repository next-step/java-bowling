package bowling.domain;

public class NormalFrame implements Frame {
    public static final int MAX_TRY_COUNT = 2;
    private BowlResult bowlResult;
    private int trying;

    private NormalFrame() {
        this.bowlResult = new BowlResult();
        this.trying = 0;
    }

    private NormalFrame(BowlResult bowlResult, int trying) {
        this.bowlResult = bowlResult;
        this.trying = trying;
    }

    public static Frame newInstance() {
        return new NormalFrame();
    }

    public Frame bowl(int downPin) {
        if (trying == 0) {
            return firstBowl(downPin);
        }
        return nextBowl(downPin);
    }

    public boolean isLastTrying() {
        return trying == MAX_TRY_COUNT;
    }

    public String getResult() {
        return bowlResult.getResult();
    }

    private Frame firstBowl(int downPin) {
        BowlResult bowlResult = BowlResult.of(downPin);
        if (bowlResult.isStrike(downPin)) {
            return new NormalFrame(bowlResult, 2);
        }
        return new NormalFrame(bowlResult, ++trying);
    }

    private Frame nextBowl(int downPin) {
        this.bowlResult = bowlResult.next(downPin);
        this.trying++;
        return this;
    }

}
