package bowling.domain;

public enum Sequential {
    NONE(0),
    DOUBLE_STRIKE(1),
    STRIKE(2),
    SPARE(3);

    int val;

    Sequential(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
