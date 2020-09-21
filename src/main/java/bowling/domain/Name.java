package bowling.domain;

public class Name {

    private static final String REGEX = "[A-Z]{3}+";
    private static final String ERR_NAME = "이름은 영문 대문자로 3자만 입력할 수 있습니다.";

    private final String value;

    public Name(String name) {
        value = name;
        verifyName();
    }

    public void verifyName() {
        if (!value.matches(REGEX)) {
            throw new IllegalArgumentException(ERR_NAME);
        }
    }
}
