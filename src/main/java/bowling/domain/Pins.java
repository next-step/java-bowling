package bowling.domain;

public class Pins {
    private int left = 10;

    public int getLeft() {
        return left;
    }

    public void fall(int count) {
        left -= count;
        if (left < 0) {
            throw new IllegalArgumentException("pins must be over 0");
        }
    }

    public boolean isAllDown() {
        return left == 0;
    }
}
