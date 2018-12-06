package domain;


public abstract class Frame {
    public static final int MAX_FRAME = 10;
    protected Pins pins = new Pins();
    public abstract Frame nextFrame();
    public abstract void addPin(Pin pin);

    @Override
    public String toString() {
        return pins.toString();
    }

    public abstract boolean isStrikePass();

    public abstract boolean isFinal();

    public abstract boolean isEmptyChane();

}
