package bowling.model;

public class Player {
    public static final int NAME_SIZE = 3;
    public static final int INITIAL_PIN_COUNT = 10;
    public static final int NO_PINS_LEFT = 0;
    public static final int INITIAL_PITCH_COUNT = 2;

    private final String name;
    private final PitchStrategy strategy;
    private int leftPinCount = INITIAL_PIN_COUNT;
    private int pitchCount = INITIAL_PITCH_COUNT;

    public Player(String name) {
        this(name, new RandomPitchStrategy());
    }

    public Player(String name, PitchStrategy strategy) {
        if (name.length() != NAME_SIZE) {
            throw new IllegalArgumentException("이름은 " + NAME_SIZE + "글자로 해주세요");
        }

        this.name = name;
        this.strategy = strategy;
    }

    public ShotResult pitch() {
        int pinDownCount = strategy.nextPinDownCount(leftPinCount);
        leftPinCount -= pinDownCount;
        pitchCount--;

        if (leftPinCount == NO_PINS_LEFT || pitchCount == 0) {
            resetPins();
        }

        return ShotResult.from(pinDownCount);
    }

    private void resetPins() {
        leftPinCount = INITIAL_PIN_COUNT;
        pitchCount = INITIAL_PITCH_COUNT;
    }

    @Override
    public String toString() {
        return name;
    }
}
