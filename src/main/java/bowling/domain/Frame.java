package bowling.domain;

public interface Frame {
    Frame bowl(int felledPins);

    int getFrameNum();

    String desc();
}
