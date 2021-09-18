package bowling.domain;

public class Name {
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > 3 || name.length() < 1) {
            throw new IllegalArgumentException("이름의 길이가 1이상 3이하로 입력해야 합니다. 이름 : " + name);
        }
    }

    public String value() {
        return name;
    }
}
