package bowling.domain;

public class Shot {
    private static final int POSSIBLE_DOWN_COUNT = 10;
    private static final int ZERO = 0;

    private int downCount;

    public Shot(int downCount) {
        if (downCount > POSSIBLE_DOWN_COUNT) {
            throw new IllegalArgumentException("down count can not over 10 ");
        }

        if (downCount < ZERO) {
            throw new IllegalArgumentException("down Count can not have nagative value");
        }

        this.downCount = downCount;
    }

    public int getDownCount() {
        return downCount;
    }

    @Override
    public String toString() {
        if (downCount == 0) {
            return "-";
        }

        return String.valueOf(downCount);
    }
}
