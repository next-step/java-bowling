package bowling.domain;

import bowling.domain.frame.Frames;

import java.util.Objects;

public class Player {

    private static final int MAX_NAME_SIZE = 3;

    private final String name;

    private final Frames frames;

    private Player(String name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public static Player from(String name) {
        validate(name);
        return new Player(name, Frames.create());
    }

    private static void validate(String name) {
        checkNameIsNullOrEmpty(name);
        checkNameSize(name);
        checkNameIsEnglish(name);
    }

    private static void checkNameIsNullOrEmpty(String name) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
    }

    private static boolean isNullOrEmpty(String name) {
        return name == null || name.isEmpty();
    }

    private static void checkNameSize(String name) {
        if (name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("이름은 3자를 넘을 수 없습니다.");
        }
    }

    private static void checkNameIsEnglish(String name) {
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("이름은 영어여야 합니다.");
        }
    }

    public Frames getFrames() {
        return frames;
    }

    public String getName() {
        return name;
    }

    public int nameLength() {
        return name.length();
    }

    public Player bowl(int score) {
        return new Player(name, frames.execute(score).calculateScores());
    }

    public boolean nowFrameEnd() {
        return frames.nowFrameEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
