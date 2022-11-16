package bowling.domain;

import java.util.regex.Pattern;

public class Player {

    private static final String NAME_REGEX = "^[A-Za-z]{3}$";

    private final String name;
    private final BowlingGame game;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.game = new BowlingGame();
    }

    private void validateName(String name) {
        if (!Pattern.matches(NAME_REGEX, name)) {
            throw new IllegalArgumentException("플레이어 이름은 영문자로 3자를 입력해야 합니다.");
        }
    }

    public void hit(int pins) {
        game.hit(pins);
    }

    public void moveNextFrame() {
        game.moveNextFrame();
    }

    public boolean isCurrentFrameEnded() {
        return game.getCurrentFrame()
                .isEnded();
    }

    public String getName() {
        return name;
    }

    public BowlingGame getGame() {
        return game;
    }

}
