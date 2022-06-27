package bowling.domain.frame;

public interface Frame {

    void bowl(int fallenPinsCount);

    boolean capableOfAdditionalBowling();

    int getScore();
}
