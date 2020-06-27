package bowling.domain;

public class Frame {
    private BowlResult bowlResult;
    private int trying;

    private Frame() {
        this.bowlResult = new BowlResult();
        this.trying = 0;
    }

    private Frame(BowlResult bowlResult, int trying) {
        this.bowlResult = bowlResult;
        this.trying = trying;
    }

    public static Frame newInstance() {
        return new Frame();
    }

    public Frame bowl(int downPin) {
        if (trying == 0) { 
            return firstBowl(downPin);
        }
        return nextBowl(downPin);
    }

    private Frame firstBowl(int downPin) {
        BowlResult bowlResult = BowlResult.of(downPin);
        if (bowlResult.isStrike(downPin)) {
            return new Frame(bowlResult, 2);
        }
        return new Frame(bowlResult, ++trying);
    }

    private Frame nextBowl(int downPin) {
        this.bowlResult = bowlResult.next(downPin);
        this.trying++;
        return this;
    }

    public boolean isLast(int lastTrying) {
        return trying == lastTrying;
    }

    public String getResult() {
        return bowlResult.getResult();
    }
}
