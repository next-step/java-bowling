package bowling.domain;

public class FinalFrame implements Frame {
    private BowlResult bowlResult;
    private int trying;
    private int maxTryingCount;

    public FinalFrame() {
        this(new BowlResult(), 0);
    }

    public FinalFrame(BowlResult bowlResult, int trying) {
        this(bowlResult, trying, 2);
    }

    public FinalFrame(BowlResult bowlResult, int trying, int maxTryingCount) {
        this.bowlResult = bowlResult;
        this.trying = trying;
        this.maxTryingCount = maxTryingCount;
    }

    public static Frame newInstance() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(int downPin) {
        validBowl();
        if (trying == 0) {
            return firstBowl(downPin);
        }
        return nextBowl(downPin);
    }

    @Override
    public boolean isLastTrying() {
        return trying == maxTryingCount;
    }

    @Override
    public String getResult() {
        return bowlResult.getResult();
    }

    private Frame firstBowl(int downPin) {
        BowlResult bowlResult = BowlResult.of(downPin);
        if (bowlResult.isStrike(downPin)) {
            return new FinalFrame(bowlResult, ++trying, 3);
        }
        return new FinalFrame(bowlResult, ++trying);
    }

    private Frame nextBowl(int downPin) {
        if (bowlResult.isSpare(downPin)) {
            this.maxTryingCount = 3;
        }
        this.bowlResult = bowlResult.next(downPin);
        this.trying++;
        return this;
    }

    private void validBowl() {
        if(trying == maxTryingCount){
            throw new IllegalStateException("투구 기회가 없습니다.");
        }
    }
}
