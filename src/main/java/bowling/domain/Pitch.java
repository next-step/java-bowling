package bowling.domain;

public abstract class Pitch {
    private final int frameNumber;
    private final int pinCount;

    protected Pitch(final int frameNumber, final int pinCount) {
        this.frameNumber = frameNumber;
        this.pinCount = pinCount;
    }

    public int getFrameNumber() {
        return frameNumber;
    }
    
    public int getPinCount() {
        return pinCount;
    }

    public abstract boolean isSpare();

    public abstract boolean isGutter();

    public abstract boolean isStrike();

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Pitch frame = (Pitch) o;
        return frameNumber == frame.frameNumber && pinCount == frame.pinCount;
    }
}
