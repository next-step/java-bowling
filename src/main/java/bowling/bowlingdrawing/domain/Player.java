package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.regex.Pattern;

public class Player {
    private final String name;
    private final Game game;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.game = new Game();
    }

    private void validateName(String name) {
        String regex = "[a-zA-z]{3}";
        if(!Pattern.matches(regex, name)) {
            throw new CustomException("player 이름은 알파벳 3글자만 허용합니다.");
        }
    }

    public void pitch(int pins) {
        game.pitch(pins);
    }
    public Game game() {
        return game;
    }

    public boolean end() {
        return game.end();
    }

    public String name() {
        return name;
    }

    public int currentFrame() {
        return game.currentFrame();
    }
}
