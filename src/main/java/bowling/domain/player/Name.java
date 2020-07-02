package bowling.domain.player;

import java.util.Objects;

public class Name {
    private static final int NAME_LENGTH = 3;

    private final String name;

    private Name(String name) {
        isNotNullOrEmpty(name);
        mustBeThreeCharacters(name.trim());
        this.name = name.trim();
    }

    private void isNotNullOrEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name 객체를 생성할 수 없습니다.");
        }
    }

    private void mustBeThreeCharacters(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("Name 객체를 생성할 수 없습니다.");
        }
    }

    public static Name of(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("%4s", name);
    }
}
