package bowling.domain;

public interface Frame {

    void bowl(Pin pin);

    boolean isFinished();

    Frame nextFrame();

}
