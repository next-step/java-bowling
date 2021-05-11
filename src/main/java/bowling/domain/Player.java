package bowling.domain;

import bowling.exception.IllegalPlayerName;

public class Player {
    public static int NAME_MAX_SIZE = 3;

    private final String name;

    public Player(String name) {
        nameValidate(name);
        this.name = name;
    }

    private void nameValidate(String name) {
        if (name.length() > NAME_MAX_SIZE) {
            throw new IllegalPlayerName("이름이 3자를 초과할수 없습니다");
        }
    }

    public String getName() {
        return name;
    }

}
