package bowling.game;

public abstract class Frame {
    protected static final String STATE_DELIMITER = "|";

    abstract int bowl(final int pinCount);

    abstract boolean hasRemainChance();

    abstract Frame createNextFrame();

    abstract FrameNumber getFrameNumber();

    abstract boolean isLastFrame();

    abstract String getStates();
}
