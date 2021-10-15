package step4.domain;

public abstract class ProtoTypeFrame implements Frame {
    private int round;

    public ProtoTypeFrame(int round) {
        this.round = round;
    }

    public int round() {
        return this.round;
    }
}
