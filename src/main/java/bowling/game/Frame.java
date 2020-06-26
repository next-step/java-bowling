package bowling.game;

public abstract class Frame {
    abstract int bowl(final int pinCount);

    abstract boolean isRemainChance();

    abstract Frame createNextFrame();

    abstract FrameNumber getFrameNumber();
}
