package bowling.domain;

public class Player {
    public static final String PLAYER_NAME_ERROR = "플레이어의 이름은 3글자로 입력해주세요.";
    private static final int PLAYER_NAME_LENGTH = 3;
    private String name;

    public Player(String name) {
        assertPlayerName(name);
        this.name = name;
    }

    private void assertPlayerName(String name) {
        if(name.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_ERROR);
        }
    }
}
