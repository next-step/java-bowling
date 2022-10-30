package bowling.domain;

public class Username {

    public static final int USERNAME_LENGTH = 3;
    private final String name;

    public Username(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("사용자 이름은 필수값입니다.");
        }
        if (name.length() != USERNAME_LENGTH) {
            throw new IllegalArgumentException("사용자 이름 길이는 " + USERNAME_LENGTH + "글자여야만 합니다. 현재 : " + name.length() + "글자");
        }
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
