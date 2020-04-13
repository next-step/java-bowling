package bowling.game.domain;

import bowling.frame.domain.Frames;
import bowling.player.domain.Player;

import java.util.stream.Stream;

public class BowlingGame {
    private static final String ALERT_WRONG_PINS = "쓰러트린 볼링핀은 0 ~ 10 사이로 입력하세요";
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final Frames game;

    private BowlingGame(Frames game) {
        this.game = game;
    }

    public static BowlingGame init(String userName) {
        return Stream.of(userName)
                .map(Player::of)
                .map(Frames::init)
                .map(BowlingGame::new)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Frames getGame() {
        return game;
    }

    public boolean isDone() {
        return game.isDone();
    }

    public void bowl(int felledPins) {
        if (felledPins < MIN_PINS || felledPins > MAX_PINS) {
            throw new IllegalArgumentException(ALERT_WRONG_PINS);
        }
        game.bowl(felledPins);
    }

}
