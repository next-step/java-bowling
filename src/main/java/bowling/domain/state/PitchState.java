package bowling.domain.state;

public interface PitchState {
    PitchState next(int point);

    boolean isNext();

    boolean isFinal();

    int getFirstPoint();

    int getSecondPoint();
}
