package bowling.step2_1.domain.frame;

public interface Frame {

    Frame pitch(int pitch);

    Frame next();

    int frameNo();

    boolean isFrameEnd();
}
