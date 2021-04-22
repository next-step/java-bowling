package bowling.domain.engine;

public interface Frame {

    void throwBall(PitchResult pitchResult);
    boolean isEnded();

}
