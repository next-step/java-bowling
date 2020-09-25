package bowling.domain;

public class Player {

    private static final String REGEX = "[A-Z]{3}+";
    private static final String ERR_NAME = "이름은 영문 대문자로 3자만 입력할 수 있습니다.";

    private final String name;

    public Player(String name) {
        this.name = name;
        verifyName();
    }

    public void verifyName() {
        if (!name.matches(REGEX)) {
            throw new IllegalArgumentException(ERR_NAME);
        }
    }

    public String getName() {
        return name;
    }
}
