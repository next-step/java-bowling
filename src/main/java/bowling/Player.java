package bowling;

import java.util.regex.Pattern;

public class Player {

    private String playerName;

    public Player(String playerName) {
        if (!checkPlayerName(playerName)) {
            throw new IllegalArgumentException("Player 이름은 3개의 영문자로 구성만 허용 됩니다");
        }
        this.playerName = playerName;
    }

    public static Player of(String playerName) {
        return new Player(playerName);
    }

    public String getName() {
        return this.playerName;
    }

    private Boolean checkPlayerName(String playerName) {
        return Pattern.matches("(^[a-zA-Z]{3})*$", playerName);
    }
}
