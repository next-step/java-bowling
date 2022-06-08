package bowling.domain.state;

public interface State {

    int NO_HIT = 0;
    int ONE_HIT = 1;
    int TWO_HIT = 2;

    State bowl(int hit);

    boolean isFinish();

    boolean hasBonusChance();

    int bowlingCount();

    String description();
}
