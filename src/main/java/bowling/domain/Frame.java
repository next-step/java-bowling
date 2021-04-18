package bowling.domain;

import bowling.exception.PinOutOfSizeException;

public class Frame {

    private Integer pins;
    private int tryCount;

    public Frame(Integer pins, int tryCount) {
        this.pins = pins;
        this.tryCount = tryCount;
    }

    public void bowl(Integer pins) {
        this.pins += pins;
        if (this.pins > 10) {
            throw new PinOutOfSizeException("10개 이상의 핀을 쓰러뜨릴 수 없습니다.");
        }
        this.tryCount++;
    }

    public int tryCount() {
        return tryCount;
    }

    public boolean nextFrame() {
        return tryCount > 1;
    }
}
