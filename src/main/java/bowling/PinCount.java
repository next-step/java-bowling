package bowling;

public class PinCount {
    private int totalPinCount = 10;
    private int firstCount = 0;
    private int secondCount = 0;


    public boolean isZero() {
        return totalPinCount == 0;
    }

    public void decreaseCount(int count, boolean first) {
        this.totalPinCount -= count;

        if (this.totalPinCount < 0) {
            throw new IllegalArgumentException("핀은 10개 이상 넘어갈 수 없습니다");
        }


        if (first) {
            this.firstCount = count;
        }

        this.secondCount = count;
    }

    public String score() {
        if (firstCount == 10) {
            return "X";
        }

        if (firstCount < 10 && totalPinCount == 0) {
            return firstCount + "|" + "/";
        }

        return firstCount + "|" + secondCount;
    }

}
