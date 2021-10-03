package bowling.domain;

public class Name {
    private static final int MAX_LENGTH = 3;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3글자를 넘을 수 없습니다. name : " + name);
        }
    }

    public String getValue() {
        return name;
    }
}
