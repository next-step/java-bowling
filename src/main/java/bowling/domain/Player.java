package bowling.domain;

public class Player {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 3;

    private String name;

    public Player(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자 이름음 null이 될 수 없습니다.");
        }

        if(name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(MINIMUM_NAME_LENGTH + "~" + MAXIMUM_NAME_LENGTH + "사이의 숫자 입니다.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}