package bowling.domain;

public interface Frame {

    boolean canBowl();

    BowlResult bowl(int value);
}
