package bowling.player.domain;

public class Name {
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
        if (name.length() != 3) {
            throw new IllegalArgumentException("Name 객체를 생성할 수 없습니다.");
        }
    }

    public static Name of(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }
}
