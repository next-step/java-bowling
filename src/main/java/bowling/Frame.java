package bowling;

public interface Frame {
    Frame next(int index);
    boolean roll(HitNumber rollNumber);
}
