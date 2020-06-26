package bowling.game;

public abstract class Frame {
    abstract int bowl(final int pinCount);

    abstract boolean hasRemainChance();

    abstract Frame createNextFrame();

    abstract FrameNumber getFrameNumber();

    abstract boolean isLastFrame();
}
