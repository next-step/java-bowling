package bowling.domain.player;

public class Player {

    private static final int NAME_MAX_LENGTH = 3;

    private final String name;
    private final int position;

    private Player(String name, int position) {
        validateName(name);
        this.name = name;
        this.position = position;
    }

    public static Player create(String name, int position) {
        return new Player(name, position);
    }

    private void validateName(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("이름을 입력해 주세요");
        }

        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 3글자로 입력해주세요");
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name;
    }
}
