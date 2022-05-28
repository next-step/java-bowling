package bowling.domain;

public class User {
    private static final int NAME_SIZE = 3;
    private final String name;

    public User(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if(name.length() != NAME_SIZE) {
            throw new IllegalArgumentException("이름 약자는 3글자만 허용합니다.");
        }
    }

    public String getName() {
        return "|  " + this.name + " |";
    }
}
