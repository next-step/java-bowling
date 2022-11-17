package bowling.domain;

public class Name {
    private final String name;


    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("이름은 3글자로 입력되어야 합니다");
        }
    }

    public String getName() {
        return name;
    }
}
