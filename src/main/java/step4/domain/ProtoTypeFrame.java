package step4.domain;

public abstract class ProtoTypeFrame implements Frame {
    private int round;

    public ProtoTypeFrame(int round) {
        this.round = round;
    }

    @Override
    public int round() {
        return this.round;
    }

}
