package bowling.game;

public interface Frame {
    String STATE_DELIMITER = "|";

    int bowl(final int pinCount);

    boolean hasRemainChance();

    Frame createNextFrame();

    FrameNumber getFrameNumber();

    boolean isLastFrame();

    String getStates();
}
