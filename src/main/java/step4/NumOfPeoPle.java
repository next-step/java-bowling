package step4;

public class NumOfPeoPle {

    private static final int MIN_NUMBER = 1;
    private static final String MIN_NUMBER_EXCEPTION = "볼링 게임에 참여하는 사람의 수는 최소 1명입니다.";
    private int numOfPeople;

    public NumOfPeoPle(int numOfPeople) {
        this.numOfPeople = numOfPeople;
        validNumber();
    }

    private void validNumber() {
        if (this.numOfPeople < MIN_NUMBER) {
            throw new IllegalArgumentException(MIN_NUMBER_EXCEPTION);
        }
    }

    public int num() {
        return numOfPeople;
    }
}
