package bowling.step2.domain;

public interface Frame {
    void pitch(int count);

    Frame nextFrame();

    boolean finished();
}
