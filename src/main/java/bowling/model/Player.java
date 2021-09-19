package bowling.model;

public class Player {
    public static final int NAME_SIZE = 3;

    private final String name;
    private final Lane lane = new Lane();

    public Player(String name) {
        if (name.length() != NAME_SIZE) {
            throw new IllegalArgumentException("이름은 " + NAME_SIZE + "글자로 해주세요");
        }

        this.name = name;
    }

    public ShotResult pitch(int pinDownCount) {
        return lane.pitch(pinDownCount);
    }

    @Override
    public String toString() {
        return name;
    }
}
